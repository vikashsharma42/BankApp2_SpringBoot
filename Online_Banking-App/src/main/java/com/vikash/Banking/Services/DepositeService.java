package com.vikash.Banking.Services;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Entity.UserTransaction;
import com.vikash.Banking.Repository.DepositeRepository;
import com.vikash.Banking.Repository.UserRepository;

@Service
public class DepositeService 
{
	@Autowired
	DepositeRepository depositeRepository;

	@Autowired
	UserRepository userRepository;

	public String deposit(double depositeAmount, Principal principal, Model model) 
	{
		User user = userRepository.findUserByUserName(principal.getName());
		Double balance = user.getBalance();
		Double userBalance = balance + depositeAmount;
		Long accountNumber = user.getAccount();

		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String localTime = dateFormat.format(currentTime);
		
		SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String localDate=localDateFormat.format(Calendar.getInstance().getTime());
		

		UserTransaction deposite = UserTransaction.builder()
				.accountNumber(accountNumber)
				.reference("deposite")
				.localDateTime(localDate+" "+localTime)
				.amount(+depositeAmount)
				.userId(principal.getName())
				.build();

		depositeRepository.save(deposite);
		userRepository.updateDepositedBalance(userBalance, accountNumber);
		String message = "Dear " + principal.getName() + " you have sucessfully deposited amount " + depositeAmount
				+ " in your account";
		model.addAttribute("message", message);
		return "transaction-success";
	}
}
