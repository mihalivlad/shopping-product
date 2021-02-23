package com.ibm.shopping.products.product.domain.dto.response;

import com.ibm.shopping.products.product.domain.entity.Product;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 12:34 PM
 * @project shopping-product
 */
public class CategoryResponseDTO {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = false)
    private String description;

    @ApiModelProperty(required = true)
    private List<Product> products = new ArrayList<>();

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(Long id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
