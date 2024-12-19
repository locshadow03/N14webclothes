package com.webclothes.webclothesservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SizeQuantityDto {
    private Long id;
    private String size;
    private int quantity;
    private String color;

    public SizeQuantityDto(Long id, String size, int quantity, String color) {
        this.id = id;
        this.size = size;
        this.quantity = quantity;
        this.color = color;
    }
}
