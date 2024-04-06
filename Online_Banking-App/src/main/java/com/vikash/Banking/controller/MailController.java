package com.vikash.Banking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vikash.Banking.Services.MailService;

@Controller
public class MailController 
{
	@Autowired
	MailService emailService;
	
	@GetMapping("/contact-us")
    public String getContactPage(Model model, Principal principal) 
    {
    	String user=principal.getName();
    	model.addAttribute("user",user);
        return "contact_form";
    }
	
	@GetMapping("/sendEmail")
	public String sendEmail(@RequestParam("email") String from, 
			                @RequestParam("subject") String subject,
			                @RequestParam("message") String msg,
			                RedirectAttributes attributes)
	{
		emailService.sendEmailMessage(from, subject, msg);
	    return "redirect:/contact-us?success=true";	
	}

}
