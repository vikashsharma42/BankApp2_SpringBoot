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
import com.vikash.Banking.Entity.UserTransaction;
import com.vikash.Banking.Repository.UserRepository;
import com.vikash.Banking.Repository.WithdrawalRepository;

@Service
public class WithdrawalService 
{
	@Autowired
	WithdrawalRepository WithdrawalRepository;
	@Autowired
	UserRepository userRepository;

	public String withdrawal(Double amount, Principal principal, Model model) {
		User user = userRepository.findUserByUserName(principal.getName());
		Double balance = user.getBalance();
		Double userBalance = balance - amount;
		Long accountNumber = user.getAccount();

		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String localTime = dateFormat.format(currentTime);
		
		SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String localDate=localDateFormat.format(Calendar.getInstance().getTime());
		
		UserTransaction withdrawal = UserTransaction.builder()
				.accountNumber(accountNumber)
				.reference("Withdrawal")
				.localDateTime(localDate+" "+localTime)
				.amount(amount * (-1))
				.userId(principal.getName())
				.build();

		WithdrawalRepository.save(withdrawal);
		
		userRepository.updateWithdrawalBalance(userBalance, accountNumber);
		String message = "Dear " + principal.getName() + " you have sucessfully withraw amount " + amount+" from your account";
		model.addAttribute("message",message);
		return "transaction-success";	
	}

	public List<UserTransaction> getWithdrawalHistory(Principal principal)
	{
		return WithdrawalRepository.getWithdrawalListByUserId(principal.getName());
	}
}
