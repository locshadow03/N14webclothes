package com.webclothes.webclothesservice.service.brand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBrandService {
    Brand addNewBrand(String nameBrand, MultipartFile imageBrand) throws SQLException, IOException;

    List<Brand> getAllBrands();

    List<String> getAllBrandTypes();

    void deleteBrand(Long brandId);

    byte[] getBrandPhotoById(Long brandId) throws SQLException;

    Brand updateBrand(Long brandId,  String nameBrand, byte[] photoBytes);

    Optional<Brand> getBrandById(Long brandId);
    long getTotalBrands();
}
