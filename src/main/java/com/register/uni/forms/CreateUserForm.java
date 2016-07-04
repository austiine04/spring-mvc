package com.register.uni.forms;

import com.register.uni.models.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class CreateUserForm {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordAgain;
    @NotEmpty
    private User.Role role = User.Role.STUDENT;
}
