package com.ibm.shopping.products.product.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.validation.constraints.Size;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 12:39 PM
 * @project shopping-product
 */

@ApiModel
public class CategoryRegistrationDTO {

    @ApiModelProperty(required = true)
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(required = true)
    @Size(max = 500)
    @Column(length = 500)
    private String description;

    public CategoryRegistrationDTO() {
    }

    public CategoryRegistrationDTO(String name, @Size(max = 500) String description) {
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
