package com.Osheen.VogueNest.controller;

import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Traditional Spring MVC Controller for Cart Operations.
 * Mapped directly to /cart to handle form actions from shop.html.
 * HTTP Referer headers to preserve active category filtering
 * across dynamic redirects.
 */
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //  method to retrieve user from active session safely
    private Long getSessionUserId(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null ? user.getId() : null;
    }

    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam String size,
            @RequestParam Integer quantity,
            HttpSession session,
            HttpServletRequest request) {

        Long userId = getSessionUserId(session);
        if (userId != null) {
            cartService.addToCart(userId, productId, size, quantity);
        }

        //  Returns user to the exact preceding filter view
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/shop");
    }

    @PostMapping("/update")
    public String updateCartItem(
            @RequestParam Long productId,
            @RequestParam String size,
            @RequestParam Integer quantity,
            HttpSession session,
            HttpServletRequest request) {

        Long userId = getSessionUserId(session);
        if (userId != null) {
            cartService.updateQuantity(userId, productId, size, quantity);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/shop");
    }

    @PostMapping("/remove")
    public String removeFromCart(
            @RequestParam Long productId,
            @RequestParam String size,
            HttpSession session,
            HttpServletRequest request) {

        Long userId = getSessionUserId(session);
        if (userId != null) {
            cartService.removeFromCart(userId, productId, size);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/shop");
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session, HttpServletRequest request) {
        Long userId = getSessionUserId(session);
        if (userId != null) {
            cartService.clearCart(userId);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/shop");
    }
}