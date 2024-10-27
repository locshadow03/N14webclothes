package com.webclothes.webclothesservice.repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    long count();

    Optional<Category> findByNameCategory(String nameCategory);

    @Query("SELECT DISTINCT c.nameCategory FROM Category c")
    List<String> findDistinctCategoryTypes();

}
