package com.meal.service;

import com.meal.domain.ProductCategory;

import java.util.List;

/**
 * Created by pengmin on 2018/1/27.
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findAll();
}
