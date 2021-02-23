package com.ibm.shopping.products.product.domain.service;

import com.ibm.shopping.products.product.domain.dto.request.CategoryRegistrationDTO;
import com.ibm.shopping.products.product.domain.dto.request.CategoryUpdateDTO;
import com.ibm.shopping.products.product.domain.dto.response.CategoryResponseDTO;

import java.util.List;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 10:47 AM
 * @project shopping-product
 */

public interface CategoryService {

    CategoryResponseDTO save(CategoryRegistrationDTO categoryRegistration);

    CategoryResponseDTO retrieveByName(String name);

    CategoryResponseDTO retrieveById(Long id);

    boolean delete(Long id);

    List<CategoryResponseDTO> getAll();

    CategoryResponseDTO update(CategoryUpdateDTO categoryUpdate, Long id);
}
