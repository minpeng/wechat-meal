package com.meal.repository;

import com.meal.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pengmin on 2018/1/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    //@Transactional //test中 完全回滚事务
    public void insertTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("中部菜系");
        productCategory.setCategoryType(4);

        productCategoryRepository.save(productCategory);

    }


    @Test
    public void updateTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("北方菜系");
        productCategory.setCategoryType(1);
        //更新
        productCategory.setCategoryId(1);
        productCategoryRepository.save(productCategory);

    }
    @Test
    public void findOneTest(){
        ProductCategory productCategory= (ProductCategory) productCategoryRepository.findOne(1);

        System.out.println(productCategory);
    }
}