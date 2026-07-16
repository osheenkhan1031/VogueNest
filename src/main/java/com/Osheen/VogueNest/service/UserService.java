package com.Osheen.VogueNest.service;

import com.Osheen.VogueNest.model.User;
import java.util.Optional;

public interface UserService {
    boolean isEmailRegistered(String email);
    void registerUser(String fullName, String email, String password);
    Optional<User> authenticate(String email, String password);
}