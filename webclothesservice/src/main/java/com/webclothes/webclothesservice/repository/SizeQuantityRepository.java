package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.SizeQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeQuantityRepository extends JpaRepository<SizeQuantity, Long> {
    void deleteByProduct_Id(Long productId);
}