package com.webclothes.webclothesservice.repository;

public interface SizeQuantityRepository extends JpaRepository<SizeQuantity, Long> {
    void deleteByProduct_Id(Long productId);
}