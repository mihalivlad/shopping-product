package com.ibm.shopping.products.product.domain.dto;

import lombok.Data;

/**
 * @author RahelaToth
 * @created 08/12/2020 - 11:29 AM
 * @project shopping-product
 */

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private String description;

    public CategoryDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
