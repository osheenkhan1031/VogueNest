package com.Osheen.VogueNest.service.impl;

import com.Osheen.VogueNest.model.Cart;
import com.Osheen.VogueNest.model.Product;
import com.Osheen.VogueNest.repository.CartRepository;
import com.Osheen.VogueNest.repository.ProductRepository;
import com.Osheen.VogueNest.service.CartService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(readOnly = true)
    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Cart addToCart(Long userId, Long productId, String size, Integer quantity) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

            Optional<Cart> existingCartOpt = cartRepository.findByUserIdAndProductIdAndSize(userId, productId, size);

            if (existingCartOpt.isPresent()) {
                Cart existingCart = existingCartOpt.get();
                existingCart.setQuantity(existingCart.getQuantity() + quantity);
                return cartRepository.save(existingCart);
            } else {
                Cart newCart = new Cart(null, userId, product, size, quantity);
                return cartRepository.save(newCart);
            }
        } catch (Exception e) {
            log.error("DEBUG ERROR in addToCart for userId: {}, productId: {}. Message: {}", userId, productId, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public Cart updateQuantity(Long userId, Long productId, String size, Integer quantity) {
        Cart cart = cartRepository.findByUserIdAndProductIdAndSize(userId, productId, size)
                .orElseThrow(() -> new IllegalArgumentException("Cart Item not found."));

        if (quantity <= 0) {
            cartRepository.deleteByUserIdAndProductIdAndSize(userId, productId, size);
            return null;
        }

        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeFromCart(Long userId, Long productId, String size) {
        cartRepository.deleteByUserIdAndProductIdAndSize(userId, productId, size);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void syncCart(Long userId, String guestCartJson) {
        if (guestCartJson == null || guestCartJson.trim().isEmpty()) {
            return;
        }
        try {
            // Safely convert local storage's dynamic JSON payload
            List<Map<String, Object>> items = objectMapper.readValue(guestCartJson, new TypeReference<List<Map<String, Object>>>() {});
            for (Map<String, Object> item : items) {
                // Defensive Shield 1: Ensure required JSON keys are present to avoid NullPointerException
                if (item.get("id") == null || item.get("size") == null || item.get("quantity") == null) {
                    log.warn("Skipping corrupt guest cart item during sync parsing: {}", item);
                    continue;
                }

                Long productId = Long.parseLong(item.get("id").toString());

                //  Skip sync if the product was deleted or doesn't exist in the database
                if (!productRepository.existsById(productId)) {
                    log.warn("Skipping guest cart product ID {} because it does not exist in the database", productId);
                    continue;
                }

                String size = item.get("size").toString();
                Integer quantity = Integer.parseInt(item.get("quantity").toString());


                this.addToCart(userId, productId, size, quantity);
            }
        } catch (Exception e) {
            log.error("Guest cart conversion failed on syncCart. Payload: {}. Error: {}", guestCartJson, e.getMessage());
        }
    }
}