package com.ibm.shopping.products.product.domain.service.impl;

import com.ibm.shopping.products.product.domain.dto.request.CategoryRegistrationDTO;
import com.ibm.shopping.products.product.domain.dto.request.CategoryUpdateDTO;
import com.ibm.shopping.products.product.domain.dto.response.CategoryResponseDTO;
import com.ibm.shopping.products.product.aop.exception.NotFoundException;
import com.ibm.shopping.products.product.domain.entity.Category;
import com.ibm.shopping.products.product.domain.repositoryInterface.CategoryRepository;
import com.ibm.shopping.products.product.domain.service.CategoryService;
import com.ibm.shopping.products.product.domain.mapper.converter.CategoryConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 12:46 PM
 * @project shopping-product
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    @Transactional
    public CategoryResponseDTO save(CategoryRegistrationDTO categoryRegistration) {
        Category category = categoryRepository.save
                (categoryConverter.generateEntityFromDTORegistration(categoryRegistration));
        return categoryConverter.generateDTOFromEntity(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO retrieveByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            throw new NotFoundException();
        }
        return categoryConverter.generateDTOFromEntity(category);
    }

    @Override
    @Transactional
    public CategoryResponseDTO retrieveById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return categoryConverter.generateDTOFromEntity(category);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Category category =  categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        categoryRepository.delete(category);
        return true;
    }

    @Override
    @Transactional
    public List<CategoryResponseDTO> getAll() {
        List<CategoryResponseDTO> categoryResponse = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        for(Category c : categories) {
            categoryResponse.add(categoryConverter.generateDTOFromEntity(c));
       }
        return categoryResponse;
    }

    @Override
    @Transactional
    public CategoryResponseDTO update(CategoryUpdateDTO categoryUpdate, Long id) {
        Category category =  categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        category.setName(categoryUpdate.getName());
        category.setDescription(categoryUpdate.getDescription());

        return categoryConverter.generateDTOFromEntity(category);
    }
}
