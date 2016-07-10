package com.register.uni.advice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.web.bind.annotation.ControllerAdvice


public class LoggedInUserControllerAdvice {

    @ModelAttribute("loggedInUser")
    public UserDetails getLoggedInUser(Authentication authentication) {
        return (authentication == null) ? null: (UserDetails) authentication.getPrincipal();
    }
}
