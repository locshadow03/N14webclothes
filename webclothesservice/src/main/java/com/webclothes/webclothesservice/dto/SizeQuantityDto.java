package com.webclothes.webclothesservice.dto;

@Data
@NoArgsConstructor
public class SizeQuantityDto {
    private Long id;
    private String size;
    private int quantity;

    public SizeQuantityDto(Long id, String size, int quantity) {
        this.id = id;
        this.size = size;
        this.quantity = quantity;
    }
}
