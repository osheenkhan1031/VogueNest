package com.Osheen.VogueNest.service.impl;

import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.repository.UserRepository;
import com.Osheen.VogueNest.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        newUser.setPassword(password);
        newUser.setRole("CUSTOMER");
        userRepository.save(newUser);
        System.out.println("DEBUG: User saved to Database!");
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            System.out.println("DEBUG: User found in DB for email: " + email);
            System.out.println("DEBUG: DB Password: " + userOpt.get().getPassword());
            System.out.println("DEBUG: Input Password: " + password);

            if (userOpt.get().getPassword().equals(password)) {
                return userOpt;
            } else {
                System.out.println("DEBUG: Password mismatch!");
            }
        } else {
            System.out.println("DEBUG: User not found in DB!");
        }
        return Optional.empty();
    }
}