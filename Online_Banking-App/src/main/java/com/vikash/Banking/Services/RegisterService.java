package com.vikash.Banking.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikash.Banking.Entity.User;
import com.vikash.Banking.Repository.UserRepository;

@Service
public class RegisterService
{
    @Autowired
    UserRepository userRepository;

    public void registerUser(User user)
    {
       userRepository.save(user);
    }
    public User findByUserName(String username)
    {
       return userRepository.findByUserName(username);
    }
    public void updatePassword(User user)
    {
       userRepository.save(user);
    }
}

