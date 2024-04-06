package com.vikash.Banking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationConfig 
{
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/home","/login", "/forgot", "/password-error", "/update-password", "/registration", "/register", "/css/**", "/images/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler())
            )
            .logout(logout -> logout
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            );
        return http.build();
    }
    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler()
    {
        return(request, response, authentication)-> 
        {
               response.sendRedirect("/home"); 
        };
    }
}
