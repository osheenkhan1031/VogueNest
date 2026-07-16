package com.Osheen.VogueNest.service;

import com.Osheen.VogueNest.model.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getCartItems(Long userId);
    Cart addToCart(Long userId, Long productId, String size, Integer quantity);
    Cart updateQuantity(Long userId, Long productId, String size, Integer quantity);
    void removeFromCart(Long userId, Long productId, String size);
    void clearCart(Long userId);
    void syncCart(Long userId, String guestCartJson);
}