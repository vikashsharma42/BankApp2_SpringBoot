package com.vikash.Banking.Model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptPassword extends BCryptPasswordEncoder
{
    public String encode(CharSequence rawPassword)
    {
        return super.encode(rawPassword);
    }
}
