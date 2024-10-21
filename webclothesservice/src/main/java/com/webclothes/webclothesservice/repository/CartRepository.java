package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long id);
}
