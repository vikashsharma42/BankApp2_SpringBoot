package com.vikash.Banking.Model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vikash.Banking.Entity.User;

@SuppressWarnings("serial")
public class UserPrincipal  implements  UserDetails 
{
    User user;
    public UserPrincipal(User user) 
    {
        this.user = user;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {

        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }
    public String getPassword() 
    {
        return user.getPassword();
    }
    public String getUsername() 
    {
        return user.getUserName().toLowerCase();
    }
    public boolean isAccountNonExpired()
    {
        return true;
    }
    public boolean isAccountNonLocked()
    {
        return true;
    }
    public boolean isCredentialsNonExpired() 
    {
        return true;
    }
    public boolean isEnabled()
    {
        return true;
    }
}
