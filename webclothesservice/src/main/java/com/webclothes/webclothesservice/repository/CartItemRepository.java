package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
