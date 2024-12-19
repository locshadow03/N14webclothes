package com.webclothes.webclothesservice.repository;

import com.webclothes.webclothesservice.model.FavoriteProduct;
import com.webclothes.webclothesservice.model.Product;
import com.webclothes.webclothesservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findByUser(User user);

    @Modifying
    @Transactional
    @Query("delete from FavoriteProduct fav where fav.product.id = :product_id")
    void deleteByProductID(@Param("product_id") Long product_id);
    Optional<FavoriteProduct> findByUserAndProduct(User user, Product product);

    @Query("SELECT fp.product, COUNT(fp.id) FROM FavoriteProduct fp WHERE FUNCTION('DATE', fp.dateAdded) = FUNCTION('DATE', CURRENT_DATE) GROUP BY fp.product ORDER BY COUNT(fp.id) DESC")
    List<Object[]> findTopFavoriteProductsToday();

    @Query("SELECT fp.product, COUNT(fp.id) FROM FavoriteProduct fp WHERE FUNCTION('MONTH', fp.dateAdded) = FUNCTION('MONTH', CURRENT_DATE) AND FUNCTION('YEAR', fp.dateAdded) = FUNCTION('YEAR', CURRENT_DATE) GROUP BY fp.product ORDER BY COUNT(fp.id) DESC")
    List<Object[]> findTopFavoriteProductsThisMonth();

    @Query("SELECT fp.product, COUNT(fp.id) FROM FavoriteProduct fp WHERE FUNCTION('YEAR', fp.dateAdded) = FUNCTION('YEAR', CURRENT_DATE) GROUP BY fp.product ORDER BY COUNT(fp.id) DESC")
    List<Object[]> findTopFavoriteProductsThisYear();

    @Query("SELECT fp.product, COUNT(fp.id) FROM FavoriteProduct fp GROUP BY fp.product ORDER BY COUNT(fp.id) DESC")
    List<Object[]> findTopFavoriteProductsOverall();



}
