package com.ibm.shopping.products.product.domain.dto.response;

import java.io.Serializable;

/**
 * @author VladCorocea
 * @created 08/12/2020 - 16:46
 * @project shopping-product
 */
public class ProductResponseShoppingServiceDTO implements Serializable {
    private Long id;
    private Double price;

    public ProductResponseShoppingServiceDTO(Long id, Double price) {
        this.id = id;
        this.price = price;
    }

    public ProductResponseShoppingServiceDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}