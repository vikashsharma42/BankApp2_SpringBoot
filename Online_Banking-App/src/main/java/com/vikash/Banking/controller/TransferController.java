package com.vikash.Banking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Services.MyUserService;
import com.vikash.Banking.Services.TransferService;

@Controller
public class TransferController {
	@Autowired
	TransferService transferService;

	@Autowired
	MyUserService userservice;

	@PostMapping("/transfer")
	public String transfer(@RequestParam("amount") double transferedAmount,
			@RequestParam("account") Long account,
			@RequestParam("transfer_account") Long transferedAccount,
			Principal principal, Model model,RedirectAttributes attributes)
	{
		User user = userservice.findUserByUserAccount(transferedAccount);
		if (user!=null)
		{
			double currentBalance = user.getBalance();
			Long userAccount= user.getAccount();
		
			if(userAccount.equals(account))
			{
				String Message="Sorry!! This is your account,you can't transfer into your account.....";
				model.addAttribute("errorMessage",Message);
				attributes.addAttribute("error","true");
	        	return "balance_error";
			}
			else if(currentBalance<transferedAmount || currentBalance<=0)
			{
				String Message="Sorry!! insufficient balance to transfer.....";
				model.addAttribute("errorMessage",Message);
				attributes.addAttribute("error","true");
	        	return "balance_error";
			}
			else 
			{
				return transferService.transfer(transferedAmount, transferedAccount, principal, model);
			}
		}
		return "account_not_found";	
	}
}
