package com.vikash.Banking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.vikash.Banking.Services.DepositeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DepositeController 
{
	@Autowired
	DepositeService depositeService;
		
	@PostMapping("/deposite")
    public String withdraw(HttpServletRequest request, Principal principal,Model model) 
	{
        double depositeAmount = Double.parseDouble(request.getParameter("amount"));
        return depositeService.deposit(depositeAmount, principal,model);
    }
}
