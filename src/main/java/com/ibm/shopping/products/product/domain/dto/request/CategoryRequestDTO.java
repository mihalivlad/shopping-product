package com.ibm.shopping.products.product.domain.dto.request;

import io.swagger.annotations.ApiModel;

/**
 * @author DariusScridon
 * @created 08/12/2020 - 2:47 PM
 * @project shopping-product
 */

@ApiModel
public class CategoryRequestDTO {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
