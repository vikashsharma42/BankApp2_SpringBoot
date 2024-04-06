package com.vikash.Banking.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService 
{
	@Autowired
	private JavaMailSender emailSender;

	public void sendEmailMessage(String from, String subject, String msg)
	{

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		try 
		{
			helper = new MimeMessageHelper(message, true);
			helper.setTo("rabishsharmamips@gmail.com");
			helper.setSubject(subject);
			helper.setText("Dear admin " + from + " send some message \n\n\n\n"
					+msg);
			helper.setSentDate(new Date());
			emailSender.send(message);
		}
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
}
