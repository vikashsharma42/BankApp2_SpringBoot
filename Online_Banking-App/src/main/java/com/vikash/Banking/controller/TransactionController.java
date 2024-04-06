package com.vikash.Banking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vikash.Banking.Entity.UserTransaction;
import com.vikash.Banking.Entity.UserTransfer;
import com.vikash.Banking.Services.DepositeService;
import com.vikash.Banking.Services.TransferService;
import com.vikash.Banking.Services.WithdrawalService;

@Controller
public class TransactionController 
{
	@Autowired
	WithdrawalService withdrawalService;
	
	@Autowired
	TransferService transferService;
	
	@Autowired
	DepositeService depositeService;
	
	@GetMapping("/transaction")
	public String getTransactionList(Model model,Principal principal)
	{
		List<UserTransaction> withdrawalList= withdrawalService.getWithdrawalHistory(principal);
		model.addAttribute("withdrawal",withdrawalList);
		
		List<UserTransfer> transferList=transferService.getTransferHistory(principal);
		model.addAttribute("transfered", transferList);
		return "transaction-list";
	}

}
