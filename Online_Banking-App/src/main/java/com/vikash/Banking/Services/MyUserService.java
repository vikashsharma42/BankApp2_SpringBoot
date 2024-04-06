package com.vikash.Banking.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Model.UserPrincipal;
import com.vikash.Banking.Repository.UserRepository;

import java.util.Locale;

@Service
public class MyUserService implements UserDetailsService 
{
    @Autowired
    UserRepository repository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
	   username.toLowerCase(Locale.ROOT);
	   User user = repository.findByUserName(username.toLowerCase(Locale.ROOT));
	   return new UserPrincipal(user);
	}

	public User findUserByUserName(String name)
	{
		User user=repository.findUserByUserName(name);
		return user;
	}

	public User findUserByUserAccount(Long transferedAccount)
	{
		User user=repository.findUserByAccount(transferedAccount);
		return user;
	}

	
}
