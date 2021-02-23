package com.ibm.shopping.products.product.domain.dto;

import com.ibm.shopping.products.product.domain.dto.request.CategoryRequestDTO;
import com.ibm.shopping.products.product.domain.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author RahelaToth
 * @created 04/12/2020 - 7:24 PM
 * @project shopping-product
 */

@Data
public class ProductDto {

    @ApiModelProperty
    private Long id;
    private String name;
    private String description;
    private double price;
    private String upc;
    private List<CategoryRequestDTO> categories;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUpc() {
        return upc;
    }

    public List<CategoryRequestDTO> getCategories() {
        return categories;
    }
}
