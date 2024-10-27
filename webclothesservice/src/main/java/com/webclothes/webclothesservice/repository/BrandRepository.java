package com.webclothes.webclothesservice.repository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    long count();

    Optional<Brand> findByName(String name);

    @Query("SELECT DISTINCT b.name FROM Brand b")
    List<String> findDistinctBrandTypes();
}
