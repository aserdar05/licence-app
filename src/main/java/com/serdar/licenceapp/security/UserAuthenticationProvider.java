package com.serdar.licenceapp.security;

import com.serdar.licenceapp.domain.Role;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.getUserByEmail(email);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(email, password, getGrantedAuthorities(user.getRole()));
        }
        else{
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
