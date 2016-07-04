package com.register.uni.services;

import com.register.uni.forms.CreateUserForm;
import com.register.uni.models.User;
import com.register.uni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

public interface UserService {
    User create(CreateUserForm createUserForm);
    User findById(Long userId);
}

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(CreateUserForm createUserForm) {
        User user = new User();
        user.setEmail(createUserForm.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(createUserForm.getPassword()));
        user.setRole(createUserForm.getRole());
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }
}
