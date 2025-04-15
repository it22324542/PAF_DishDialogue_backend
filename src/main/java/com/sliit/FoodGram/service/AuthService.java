package com.sliit.FoodGram.service;

import com.sliit.FoodGram.model.User;
import com.sliit.FoodGram.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProvider("LOCAL");
        return userRepository.save(user);
    }

    public Optional<User> authenticate(String email, String rawPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getEmail());
            boolean passwordMatch = passwordEncoder.matches(rawPassword, user.get().getPassword());
            System.out.println("Password match: " + passwordMatch);
            if (passwordMatch) {
                return user;
            } else {
                System.out.println("Password mismatch for user: " + email);
            }
        } else {
            System.out.println("No user found for email: " + email);
        }
        return Optional.empty();
    }
}