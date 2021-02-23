package com.ibm.shopping.products.product.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author DariusScridon
 * @created 07/12/2020 - 4:25 PM
 * @project shopping-product
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long id){
        super(String.format("Category with id %d not found!", id));
    }
}
