package com.meal.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by pengmin on 2018/1/28.
 */
@Table(name = "t_product_info")
@Entity
public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer product_stock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(Integer product_stock) {
        this.product_stock = product_stock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductInfo{");
        sb.append("productId='").append(productId).append('\'');
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productPrice=").append(productPrice);
        sb.append(", product_stock=").append(product_stock);
        sb.append(", productDescription='").append(productDescription).append('\'');
        sb.append(", productIcon='").append(productIcon).append('\'');
        sb.append(", productStatus=").append(productStatus);
        sb.append(", categoryType=").append(categoryType);
        sb.append('}');
        return sb.toString();
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }


}
