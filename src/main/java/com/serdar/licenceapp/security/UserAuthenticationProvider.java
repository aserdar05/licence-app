package com.serdar.licenceapp.security;

import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    public UserAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.getUserByEmailAndPassword(email, password);
        if(user != null){
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        }
        else{
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
