package com.webclothes.webclothesservice.service.favorite;

import com.webclothes.webclothesservice.dto.FavoriteProductResponse;
import com.webclothes.webclothesservice.model.FavoriteProduct;
import com.webclothes.webclothesservice.model.Product;
import com.webclothes.webclothesservice.model.User;
import com.webclothes.webclothesservice.repository.FavoriteProductRepository;
import com.webclothes.webclothesservice.repository.ProductRepository;
import com.webclothes.webclothesservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteProductImpl implements IFavoriteProductService {
    private final FavoriteProductRepository favoriteProductRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;


    @Override
    public List<FavoriteProduct> getAllFavoriteProductsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tồn tại tài khoản!"));
        return favoriteProductRepository.findByUser(user);
    }


    @Override
    public FavoriteProduct addProductToFavorites(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản!"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm!"));

        if (favoriteProductRepository.findByUserAndProduct(user, product).isPresent()) {
            throw new RuntimeException("Sản phẩm đã được thêm vào ưu thích rồi");
        }

        FavoriteProduct favoriteProduct = new FavoriteProduct();

        favoriteProduct.setProduct(product);
        favoriteProduct.setUser(user);

        return favoriteProductRepository.save(favoriteProduct);
    }

    @Override
    public void removeProductFromFavorites(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        FavoriteProduct favoriteProduct = favoriteProductRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong yêu thích!"));

        favoriteProductRepository.delete(favoriteProduct);
    }

    public List<FavoriteProductResponse> getTopFavoriteProductByToday() {
        List<Object[]> results = favoriteProductRepository.findTopFavoriteProductsToday();
        return results.stream()
                .map(result -> new FavoriteProductResponse((Product) result[0], Math.toIntExact((Long) result[1])))
                .collect(Collectors.toList());
    }

    public List<FavoriteProductResponse> getTopFavoriteProductByMonth() {
        List<Object[]> results = favoriteProductRepository.findTopFavoriteProductsThisMonth();
        return results.stream()
                .map(result -> new FavoriteProductResponse((Product) result[0], Math.toIntExact((Long) result[1])))
                .collect(Collectors.toList());
    }

    public List<FavoriteProductResponse> getTopFavoriteProductByYear() {
        List<Object[]> results = favoriteProductRepository.findTopFavoriteProductsThisYear();
        return results.stream()
                .map(result -> new FavoriteProductResponse((Product) result[0], Math.toIntExact((Long) result[1])))
                .collect(Collectors.toList());
    }

    public List<FavoriteProductResponse> getTopFavoriteProductByAll() {
        List<Object[]> results = favoriteProductRepository.findTopFavoriteProductsOverall();
        return results.stream()
                .map(result -> new FavoriteProductResponse((Product) result[0], Math.toIntExact((Long) result[1])))
                .collect(Collectors.toList());
    }
}
