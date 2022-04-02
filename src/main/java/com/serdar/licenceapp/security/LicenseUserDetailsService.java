package com.serdar.licenceapp.security;

import com.serdar.licenceapp.services.UserService;
import com.serdar.licenceapp.services.UserServiceImp;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LicenseUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public LicenseUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.serdar.licenceapp.domain.User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDetails userDetails = User.withUsername(user.getEmail()).password(user.getPassword()).authorities("USER").build();
        return userDetails;
    }
}
