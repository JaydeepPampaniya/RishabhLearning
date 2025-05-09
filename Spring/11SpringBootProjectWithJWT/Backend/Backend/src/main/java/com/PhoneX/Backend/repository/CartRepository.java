package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Cart;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUserAndProducts(User user, Product products);


    Optional<List<Cart>> findByUser(User user);

    @Transactional
    void deleteByUserId(Long userId);

}
