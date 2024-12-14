package com.webclothes.webclothesservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String nameCategory;

    private String imageCategory;

    public CategoryDto(Long id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    public CategoryDto(Long id, String nameCategory, byte[] photoBytes) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.imageCategory = photoBytes != null ? Base64.getEncoder().encodeToString(photoBytes) : null;
    }

}
