package com.webclothes.webclothesservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productName;
    private String size;
    private Integer quantity;
    private String color;
    private double price;
    private double disCount;
    private String imageProduct;
}
