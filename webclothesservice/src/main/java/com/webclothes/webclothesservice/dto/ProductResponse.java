package com.webclothes.webclothesservice.dto;

import com.webclothes.webclothesservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Product product;
    private Integer totalProduct;
}