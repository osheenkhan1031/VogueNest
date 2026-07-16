package com.Osheen.VogueNest.controller;

import com.Osheen.VogueNest.model.Cart;
import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CartRestController - VogueNest
 * Exposes REST APIs for AJAX asynchronous operations in shop.html drawer.
 */
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartRestController {

    private final CartService cartService;

    private Long getLoggedInUserId(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return (user != null) ? user.getId() : null;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getCart(HttpSession session) {
        Long userId = getLoggedInUserId(session);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestParam Long productId,
            @RequestParam String size,
            @RequestParam Integer quantity,
            HttpSession session) {

        Long userId = getLoggedInUserId(session);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart item = cartService.addToCart(userId, productId, size, quantity);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(
            @RequestParam Long productId,
            @RequestParam String size,
            @RequestParam Integer quantity,
            HttpSession session) {

        Long userId = getLoggedInUserId(session);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart item = cartService.updateQuantity(userId, productId, size, quantity);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFromCart(
            @RequestParam Long productId,
            @RequestParam String size,
            HttpSession session) {

        Long userId = getLoggedInUserId(session);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        cartService.removeFromCart(userId, productId, size);
        return ResponseEntity.ok().build();
    }
}