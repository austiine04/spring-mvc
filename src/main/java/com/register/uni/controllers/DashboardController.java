package com.register.uni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @RequestMapping("/")
    public ModelAndView get() {
        return new ModelAndView("dashboard");
    }
}
