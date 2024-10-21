package com.webclothes.webclothesservice.service.cart;

import com.webclothes.webclothesservice.model.Cart;

import java.util.Optional;

public interface ICartService {

    Cart createCart(Long customerId);

    Optional<Cart> getcardById(Long cartId);

    Cart getCartByCustomerId(Long customerId);
    Cart addProductToCart(Long cartId, Long productId, int quantity, String size);
    void removeProductFromCart(Long cartId, Long cartItemId);
    void updateProductQuantity(Long cartId, Long cartItemId, int quantity, String size);
}
