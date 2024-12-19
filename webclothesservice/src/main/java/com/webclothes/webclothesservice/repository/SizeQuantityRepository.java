package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.SizeQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SizeQuantityRepository extends JpaRepository<SizeQuantity, Long> {
    void deleteByProduct_Id(Long productId);

    @Query("select sq from SizeQuantity sq where sq.product.id = :product_id and sq.size = :size")
    SizeQuantity findByIdProductAndSize(@Param("product_id") Long product_id, @Param("size") String Size);
}