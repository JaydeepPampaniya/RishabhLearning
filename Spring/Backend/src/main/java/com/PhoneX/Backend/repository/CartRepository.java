package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1 AND c.products.id = ?2")
    Optional<Cart> findByUserIdAndProductId(long userId, long productId);

    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    Optional<List<Cart>> findByUserId(long userId);
}
