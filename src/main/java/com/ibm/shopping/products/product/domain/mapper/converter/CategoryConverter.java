package com.ibm.shopping.products.product.domain.mapper.converter;

import com.ibm.shopping.products.product.domain.dto.request.CategoryRegistrationDTO;
import com.ibm.shopping.products.product.domain.dto.response.CategoryResponseDTO;
import com.ibm.shopping.products.product.domain.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 12:54 PM
 * @project shopping-product
 */
@Component
public class CategoryConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Category generateEntityFromDTORegistration(CategoryRegistrationDTO categoryRegistrationDTO) {
        return modelMapper.map(categoryRegistrationDTO, Category.class);
    }

    public CategoryResponseDTO generateDTOFromEntity(Category category) {
        return modelMapper.map(category, CategoryResponseDTO.class);
    }
}
