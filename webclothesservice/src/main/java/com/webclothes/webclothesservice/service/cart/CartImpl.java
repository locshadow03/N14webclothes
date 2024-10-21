package com.webclothes.webclothesservice.service.cart;

import com.webclothes.webclothesservice.model.Cart;
import com.webclothes.webclothesservice.repository.CartItemRepository;
import com.webclothes.webclothesservice.repository.CartRepository;

import java.util.Optional;

public class CartImpl implements ICartService{

    private final CartRepository cartRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

    @Override
    public Cart createCart(Long customerId) {
        return null;
    }

    @Override
    public Optional<Cart> getcardById(Long cartId) {
        return Optional.empty();
    }

    @Override
    public Cart getCartByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Cart addProductToCart(Long cartId, Long productId, int quantity, String size) {
        return null;
    }

    @Override
    public void removeProductFromCart(Long cartId, Long cartItemId) {

    }

    @Override
    public void updateProductQuantity(Long cartId, Long cartItemId, int quantity, String size) {

    }
}
