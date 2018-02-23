package com.meal.service;

import com.meal.domain.ProductCategory;

import java.util.List;

/**
 * Created by pengm on 2018/2/22.
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}