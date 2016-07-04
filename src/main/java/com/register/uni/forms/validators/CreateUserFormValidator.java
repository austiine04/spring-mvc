package com.register.uni.forms.validators;

import com.register.uni.forms.CreateUserForm;
import com.register.uni.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateUserFormValidator implements Validator {

    private UserService userService;

    @Autowired
    public CreateUserFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CreateUserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateUserForm form = (CreateUserForm) target;
        validateEmail(errors, form);
        validatePasswords(errors, form);
    }

    private void validateEmail(Errors errors, CreateUserForm form) {
        if (userService.findByEmail(form.getEmail()).isPresent()) {
            errors.rejectValue("email.exists", "A user with this email address already exists");
        }
    }

    private void validatePasswords(Errors errors, CreateUserForm form) {
        if (!(form.getPassword().equals(form.getPasswordAgain()))) {
            errors.rejectValue("password.no_match", "Password mismatch");
        }
    }
}
