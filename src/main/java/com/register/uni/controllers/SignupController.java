package com.register.uni.controllers;

import com.register.uni.forms.CreateUserForm;
import com.register.uni.forms.validators.CreateUserFormValidator;
import com.register.uni.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignupController {

    private UserService userService;
    private CreateUserFormValidator validator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public SignupController(UserService userService, CreateUserFormValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String get(Model model) {
        model.addAttribute("form", new CreateUserForm());
        return "signup";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ModelAndView post(@Valid @ModelAttribute("form")
                             CreateUserForm form, BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("signup");
        }

        userService.create(form);
        return new ModelAndView("redirect:/login");
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ModelAndView duplicateEmailHandler(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email.exists", "Email already exists");
        modelAndView.setViewName("signup");
        return modelAndView;
    }

}
