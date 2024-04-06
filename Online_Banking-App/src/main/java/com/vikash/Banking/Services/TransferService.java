package com.vikash.Banking.Services;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Entity.UserTransfer;
import com.vikash.Banking.Repository.TransferRepository;
import com.vikash.Banking.Repository.UserRepository;

@Service
public class TransferService
{
	@Autowired
	TransferRepository transferRepository;
	
	@Autowired
	UserRepository userRepository;

	
	public String transfer(double transferedAmount, Long transferedAccount, Principal principal, Model model)
	{
		User user = userRepository.findUserByUserName(principal.getName());
		Double balance = user.getBalance();
		Double userBalance = balance - transferedAmount;
		
		Double transferMoney=transferedAmount*(-1);
		
		User user2=userRepository.findUserByAccount(transferedAccount);
		Double amount = user2.getBalance();
		Double userAmount = amount + transferedAmount;
		
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String localTime = dateFormat.format(currentTime);
		
		SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String localDate=localDateFormat.format(Calendar.getInstance().getTime());
		
		UserTransfer tranjection=UserTransfer.builder()
				.accountNumber(user.getAccount())
				.amount(transferMoney)
				.beneficiaryAccount(transferedAccount)
				.localDateTime(localDate+" "+localTime)
				.refrence("transfer")
				.userId(principal.getName())
				.build();
		transferRepository.save(tranjection);
		
		userRepository.updateTransferBalance(userBalance, user.getAccount());
		
		userRepository.updateTransferBalance(userAmount,user2.getAccount());
		
		String message = "Dear " + principal.getName() + " you have sucessfully transfered amount " + transferedAmount
				+ " to "+user2.getUserName();
		model.addAttribute("message", message);
		return "transaction-success";
	}
	
	public List<UserTransfer> getTransferHistory(Principal principal)
	{
		return transferRepository.getTransferListByUserId(principal.getName());
	}

}
