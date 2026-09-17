package com.Osheen.VogueNest.service.impl;

import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.repository.UserRepository;
import com.Osheen.VogueNest.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Initialize BCrypt Password Encoder for secure hashing
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public void registerUser(String fullName, String email, String password) {
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);

        // FIX: Securely hash the password before saving to the database
        newUser.setPassword(passwordEncoder.encode(password));

        newUser.setRole("CUSTOMER");
        userRepository.save(newUser);
        System.out.println("DEBUG: User saved to Database with secure password hash!");
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            // FIX: Removed dangerous debug logs that printed raw passwords to the console.

            // FIX: Use passwordEncoder.matches() to safely evaluate input against the stored BCrypt hash
            if (passwordEncoder.matches(password, userOpt.get().getPassword())) {
                return userOpt;
            } else {
                System.out.println("DEBUG: Password mismatch for email: " + email);
            }
        } else {
            System.out.println("DEBUG: User not found in DB for email: " + email);
        }

        return Optional.empty();
    }
}