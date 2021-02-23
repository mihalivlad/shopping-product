package com.ibm.shopping.products.product.domain.dto.response;

import lombok.Data;

/**
 * @author DariusScridon
 * @created 09/12/2020 - 2:11 PM
 * @project shopping-product
 */

@Data
public class ProductResponseToUserDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String upc;

}
