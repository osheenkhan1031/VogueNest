package com.Osheen.VogueNest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.Osheen.VogueNest.repository.UserRepository;
import com.Osheen.VogueNest.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    //  Login Page
    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    //  Signup Page
    @GetMapping("/signup")
    public String showSignup(){
        return "signup";
    }

    //  Categories Page
    @GetMapping("/categories")
    public String showCategories() {
        return "categories";
    }

    // Signup Logic
    @PostMapping("/signup")
    public String registerUser(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()) {
            model.addAttribute("error", "This email is already registered!");
            return "signup";
        }
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        userRepository.save(newUser);
        return "redirect:/login?success=true";
    }
}