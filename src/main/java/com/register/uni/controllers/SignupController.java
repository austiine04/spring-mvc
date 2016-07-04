package com.register.uni.controllers;

import com.register.uni.forms.CreateUserForm;
import com.register.uni.models.User;
import com.register.uni.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    private UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("form", new CreateUserForm());
        return "signup";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute CreateUserForm createUserForm) {
        User user = userService.create(createUserForm);
        String redirectUrl = String.format("redirect:/users/%d", user.getId());
        return new ModelAndView(redirectUrl);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String user(Model model, @PathVariable("id")  Long id) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

}
