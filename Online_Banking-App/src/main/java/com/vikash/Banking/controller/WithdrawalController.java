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
import com.vikash.Banking.Services.WithdrawalService;


@Controller
public class WithdrawalController 
{
	@Autowired
	WithdrawalService withdrawalService;
	@Autowired
	MyUserService userservice;
		
	@PostMapping("/withdraw")
    public String withdraw(@RequestParam ("amount") double withdrawalAmount, Principal principal,Model model, RedirectAttributes attributes) 
	{
        User user = userservice.findUserByUserName(principal.getName());
        double currentBalance = user.getBalance();
        if(currentBalance < withdrawalAmount || currentBalance<=0)
        {
        	String Message="Sorry!! insufficient balance to Withdraw.....";
			model.addAttribute("errorMessage",Message);
        	attributes.addAttribute("error","true");
        	return "balance_error";
        }
        else
        {
        	return withdrawalService.withdrawal(withdrawalAmount, principal, model);
        }
    }
}
