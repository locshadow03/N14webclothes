package com.webclothes.webclothesservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartItem")
public class CartItem {

    private Long id;

    private Cart cart;

    private int quantity;
    private Double price;
    private String size;

    public CartItem(Long id, Cart cart, int quantity, Double price, String size) {
        this.id = id;
        this.cart = cart;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
    }
}
