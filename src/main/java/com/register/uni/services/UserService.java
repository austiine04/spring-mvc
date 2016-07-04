package com.register.uni.services;

import com.register.uni.forms.CreateUserForm;
import com.register.uni.models.User;
import com.register.uni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    User create(CreateUserForm createUserForm);
    User findById(Long userId);
    Optional<User> findByEmail(String email);
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

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
