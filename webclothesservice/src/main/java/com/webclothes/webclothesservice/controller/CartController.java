package com.webclothes.webclothesservice.controller;

import com.webclothes.webclothesservice.dto.CartDto;
import com.webclothes.webclothesservice.model.Cart;
import com.webclothes.webclothesservice.model.CartItem;
import com.webclothes.webclothesservice.model.SizeQuantity;
import com.webclothes.webclothesservice.repository.SizeQuantityRepository;
import com.webclothes.webclothesservice.service.cart.ICartService;
import com.webclothes.webclothesservice.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/home/cart")
public class CartController {
    private final ICartService cartService;
    private final IProductService productService;
    private final SizeQuantityRepository sizeQuantityRepository;

    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(@RequestParam Long userId) {
        Cart cart = cartService.createCart(userId);

        CartDto cartDto = new CartDto();

        cartDto.setStatus("Tạo giỏ hàng thành công");
        cartDto.setCustomerId(cart.getCustomer().getId());
        cartDto.setCartId(cart.getCartId());
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartDto>> getCartById(@PathVariable Long cartId) throws SQLException {
        Optional<Cart> optionalCart = cartService.getCartById(cartId);
        Cart cart = optionalCart.get();
        List<CartItem> cartItems = cart.getCartItems();
        List<CartDto> cartDtos = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            byte[] photoBytes = productService.getProductPhotoById(cartItem.getProduct().getId());
            if (photoBytes != null && photoBytes.length > 0) {
                String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
                CartDto cartDto = new CartDto();
                cartDto.setProductId(cartItem.getProduct().getId());
                cartDto.setCartId(cartItem.getId());
                cartDto.setNameProduct(cartItem.getProduct().getName());
                cartDto.setPrice(cartItem.getPrice());
                cartDto.setSize(cartItem.getSize());
                SizeQuantity sizeQuantity = sizeQuantityRepository.findByIdProductAndSize(cartItem.getProduct().getId(), cartItem.getSize());
                cartDto.setColor(sizeQuantity.getColor());
                cartDto.setQuantity(cartItem.getQuantity());
                cartDto.setImageProduct(base64Photo);
                cartDto.setDisCount(cartItem.getProduct().getDisCount());
                cartDtos.add(cartDto);
            }
        }
        return ResponseEntity.ok(cartDtos);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable Long cartId,
                                                    @RequestParam Long productId,
                                                    @RequestParam int quantity,
                                                    @RequestParam String size) {

        Cart cart = cartService.addProductToCart(cartId, productId, quantity, size);

        // Tạo DTO để trả về
        CartDto cartDto = new CartDto();
        cartDto.setStatus("201");
        cartDto.setCustomerId(cart.getCustomer().getId());
        cartDto.setQuantity(quantity);
        cartDto.setSize(size);
        cartDto.setProductId(productId);

        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartService.removeProductFromCart(cartId, cartItemId);
    }

    @PutMapping("/{cartId}/update/{cartItemId}")
    public void updateProductQuantity(@PathVariable Long cartId, @PathVariable Long cartItemId, @RequestParam int quantity, @RequestParam String size) {
        cartService.updateProductQuantity(cartId, cartItemId, quantity, size);
    }
}

