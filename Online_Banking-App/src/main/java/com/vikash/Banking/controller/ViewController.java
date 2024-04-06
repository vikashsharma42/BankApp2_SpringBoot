package com.vikash.Banking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Services.MyUserService;

@Controller
public class ViewController 
{
    @Autowired
    MyUserService userservice;

    @GetMapping("/home")
    public String getHome(Model model, Principal principal)
    {
    	if (principal != null)
    	{
    		String userName = principal.getName();
            model.addAttribute("userName", userName);
            return "index";
    	}
    	else
    	{
    		return "index";
    	}
    }
    @GetMapping("/login")
    public String getLoginPage() 
    {
        return "login_form";
    }
    @GetMapping("/register")
    public String getRegisterationPage()
    {
        return "register";
    }
    @GetMapping("/forgot")
    public String getForgotPage()
    {
        return "forgot-password-form";
    }
    @GetMapping("/error")
    public String getErrorPage()
    {
        return "error";
    }
    @GetMapping("/password-error")
    public String getPasswordError() 
    {

        return "password_error";
    }
    @GetMapping("/dashboard")
    public String getDashboard(Model model, Principal principal) 
    {
    	User user=userservice.findUserByUserName(principal.getName());
    	model.addAttribute("user",user);
        return "user-dashboard";
    }
    @GetMapping("/transactionSucess")
	public String getTransactionSuccessPage()
	{
	   return "transaction-message";	
	}
}
