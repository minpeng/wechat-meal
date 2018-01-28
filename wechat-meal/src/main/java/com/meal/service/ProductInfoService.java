package com.meal.service;

import com.meal.domain.ProductInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by pengmin on 2018/1/28.
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findAll();

    List<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
}
