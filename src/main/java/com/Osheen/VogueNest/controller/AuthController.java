package com.Osheen.VogueNest.controller;

import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.service.UserService;
import com.Osheen.VogueNest.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value; // Import properly to read properties
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final CartService cartService;

    // Dynamically checks dynamic admin role fallback checks
    @Value("${app.admin.email}")
    private String adminEmail;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @GetMapping("/categories")
    public String showCategories() {
        return "categories";
    }

    @PostMapping("/auth/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam(required = false) String guestCartJson,
                            HttpSession session,
                            Model model) {

        Optional<User> authenticatedUser = userService.authenticate(email, password);

        if (authenticatedUser.isPresent()) {
            User user = authenticatedUser.get();
            session.setAttribute("loggedInUser", user);

            if (guestCartJson != null && !guestCartJson.trim().isEmpty()) {
                cartService.syncCart(user.getId(), guestCartJson);
                session.setAttribute("clearLocalCartFlag", true);
            }

            // Secure validation mapping that evaluates properties safely alongside DB roles
            if ("ADMIN".equalsIgnoreCase(user.getRole()) || adminEmail.equalsIgnoreCase(user.getEmail())) {
                return "redirect:/admin/add";
            }

            String redirectUrl = (String) session.getAttribute("redirectUrl");
            if (redirectUrl != null) {
                session.removeAttribute("redirectUrl");
                return "redirect:" + redirectUrl;
            }

            return "redirect:/categories";
        }

        model.addAttribute("error", "Invalid email or password!");
        return "login";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {

        if (userService.isEmailRegistered(email)) {
            redirectAttributes.addFlashAttribute("error", "This email is already registered!");
            return "redirect:/signup";
        }

        userService.registerUser(fullName, email, password);
        redirectAttributes.addFlashAttribute("success", "Account created successfully! Please log in.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}