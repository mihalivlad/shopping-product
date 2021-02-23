package com.ibm.shopping.products.product.domain.service.impl;

import com.ibm.shopping.products.product.aop.exception.NotFoundException;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.dto.request.CategoryRequestDTO;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseShoppingServiceDTO;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseToUserDTO;
import com.ibm.shopping.products.product.domain.entity.Category;
import com.ibm.shopping.products.product.domain.entity.Product;
import com.ibm.shopping.products.product.domain.mapper.ProductMapper;
import com.ibm.shopping.products.product.domain.mapper.converter.CategoryConverter;
import com.ibm.shopping.products.product.domain.repository.ProductJpaRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.CategoryRepository;
import com.ibm.shopping.products.product.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RahelaToth
 * @created 07/12/2020 - 12:32 PM
 * @project shopping-product
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Autowired
    public ProductServiceImpl(final ProductJpaRepository productJpaRepository, final ProductMapper productMapper, CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        if(!productJpaRepository.existsById(productDto.getId())){
            String sb = "Item with id: " + productDto.getId() +
                    " is not found!";
            throw new NotFoundException(sb);
        }
        Product product = productJpaRepository.save(productMapper.dto2Entity(productDto));
        return productMapper.entity2Dto(product);
    }

    @Override
    public ProductDto delete(long id) {
        Product product = productJpaRepository.getOne(id);
        if(!productJpaRepository.existsById(id)){
            String sb = "Item with id: " + id +
                    " is not found!";
            throw new NotFoundException(sb);
        }
        productJpaRepository.delete(product);
        return productMapper.entity2Dto(product);
    }

    @Transactional
    public ProductDto save(ProductDto dto) {
        List<CategoryRequestDTO> categoriesId = dto.getCategories();
        List<Category> categories = new ArrayList<>();

        for (CategoryRequestDTO c : categoriesId) {
            Category category = categoryRepository.getOne(c.getId());
            categories.add(category);
        }
        Product product = productMapper.dto2Entity(dto);
        product.setCategories(categories);

        return productMapper.entity2Dto(productJpaRepository.save(product));
    }


    @Override
    @Transactional
    public ProductDto getProductById(Long id) {
        Product product = productJpaRepository.getOne(id);
        if(!productJpaRepository.existsById(id)){
            throw new NotFoundException();
        }
        return productMapper.entity2Dto(product);
    }

    @Override
    @Transactional
    public List<ProductDto> getAll() {
        List<Product> products = productJpaRepository.findAll();
        return productMapper.entityList2DtoList(products);
    }

    @Override
    @Transactional
    public List<ProductResponseShoppingServiceDTO> verifyProducts(List<Long> ids) {
        List<ProductResponseShoppingServiceDTO> list = new ArrayList<>();
        for(Long id: ids){
            if(!productJpaRepository.existsById(id)){
                throw new NotFoundException();
            }
            Product product = productJpaRepository.getOne(id);
            ProductResponseShoppingServiceDTO productResponseShoppingServiceDTO = new ProductResponseShoppingServiceDTO(id,product.getPrice());
            list.add(productResponseShoppingServiceDTO);
        }
        return list;
    }

    @Override
    @Transactional
    public List<ProductResponseToUserDTO> getProductsByCategory(Long id) {
        Category category = categoryRepository.getOne(id);
        List<ProductResponseToUserDTO> products = productMapper.entityListToDTOList(category.getProducts());

        return products;
    }
}
