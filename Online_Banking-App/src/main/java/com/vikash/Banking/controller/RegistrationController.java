package com.vikash.Banking.controller;



import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Model.EncryptPassword;
import com.vikash.Banking.Services.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController 
{
    EncryptPassword encryptPassword;
    RegisterService registerService;
    public RegistrationController(EncryptPassword encryptPassword, RegisterService registerService) 
    {
        this.encryptPassword = encryptPassword;
        this.registerService = registerService;
    }
   @PostMapping("/registration")
    public String Register(HttpServletRequest request, HttpSession session) 
    {
    	Random random=new Random();
    	Long account=(long)random.nextInt(9999999)*99999999;
    	double balance=2000.00;
    	String password=request.getParameter("password");
	    String username=request.getParameter("username").toLowerCase();
	    String fname=request.getParameter("fname").toLowerCase();
	    String lname=request.getParameter("lname").toLowerCase();
	    String city=request.getParameter("city").toLowerCase();
	    String age=request.getParameter("age").toLowerCase();
	    String phone=request.getParameter("phone").toLowerCase();
	    String email=request.getParameter("email").toLowerCase();
        String  confirmPassword =   request.getParameter("confirm_password");
        if (password.equals(confirmPassword)) 
        {
           String hashedPassword = encryptPassword.encode(password);
           User user = User.builder()
            	    .userName(username)
            	    .password(hashedPassword)
            	    .firstName(fname)
            	    .lastName(lname)
            	    .city(city)
            	    .age(age)
            	    .phone(phone)
            	    .email(email)
            	    .account(account)
            	    .balance(balance)
            	    .build();
            registerService.registerUser(user);
            return "login_form";
        }
        else
        {
            return "password_error";
        }
    }
   @PostMapping("/update-password")
   public String updatePassword(HttpServletRequest request, HttpSession session,Model model)
   {
       String password = request.getParameter("password");
       String username = request.getParameter("username").toLowerCase();
       String hashedPassword = encryptPassword.encode(password);
       User user = registerService.findByUserName(username);
       if (user != null) {
           user.setPassword(hashedPassword);
           registerService.updatePassword(user);
           return"login_form";
       }
       model.addAttribute("msg","User not found");
       return "forgot-password-form";
   }
}

