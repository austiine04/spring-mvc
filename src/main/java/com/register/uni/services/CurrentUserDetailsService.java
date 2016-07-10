package com.register.uni.services;

import com.register.uni.models.CurrentUser;
import com.register.uni.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user  = userService.
                findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s was not found", email)));
        return new CurrentUser(user);
    }
}
