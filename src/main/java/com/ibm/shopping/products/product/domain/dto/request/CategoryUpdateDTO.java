package com.ibm.shopping.products.product.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 12:42 PM
 * @project shopping-product
 */

@ApiModel
public class CategoryUpdateDTO {
    @ApiModelProperty(required = true)
    @Size(max = 32)
    private String name;

    @ApiModelProperty(required = false)
    @Size(max = 500)
    private String description;

    public CategoryUpdateDTO() {
    }

    public CategoryUpdateDTO(@Size(max = 32) String name, @Size(max = 500) String description) {
        this.name = name;
        this.description = description;
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
}
