package com.Osheen.VogueNest.repository;

import com.Osheen.VogueNest.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserIdAndProductIdAndSize(Long userId, Long productId, String size);

    /**
     * Optimized bulk delete query for MySQL.
     * Prevents lock wait timeouts and executing individual select-before-delete actions.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.userId = :userId AND c.product.id = :productId AND c.size = :size")
    void deleteByUserIdAndProductIdAndSize(
            @Param("userId") Long userId,
            @Param("productId") Long productId,
            @Param("size") String size
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}