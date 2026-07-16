package com.Osheen.VogueNest.controller;

import com.Osheen.VogueNest.model.Cart;
import com.Osheen.VogueNest.model.User;
import com.Osheen.VogueNest.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final CartService cartService;

    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");

        // If not logged in, save destination route and redirect to User login
        if (user == null) {
            session.setAttribute("redirectUrl", "/checkout");
            return "redirect:/login";
        }

        List<Cart> cartItems = cartService.getCartItems(user.getId());
        double subtotal = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("user", user);

        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        // clean user's database active bag records on successful checkout
        cartService.clearCart(user.getId());
        return "redirect:/order-success";
    }

    @GetMapping("/order-success")
    public String orderSuccess(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        return "order-success";
    }
}