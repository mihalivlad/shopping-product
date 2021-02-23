package com.ibm.shopping.products.product.unit;

import com.ibm.shopping.products.product.ProductApplication;
import com.ibm.shopping.products.product.aop.exception.NotFoundException;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.entity.Product;
import com.ibm.shopping.products.product.domain.mapper.ProductMapper;
import com.ibm.shopping.products.product.domain.mapper.ProductMapperImpl;
import com.ibm.shopping.products.product.domain.mapper.converter.CategoryConverter;
import com.ibm.shopping.products.product.domain.repository.ProductJpaRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.CategoryRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.ProductRepository;
import com.ibm.shopping.products.product.domain.service.ProductService;
import com.ibm.shopping.products.product.domain.service.impl.ProductServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.spec.DSAPrivateKeySpec;
import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author VladCorocea
 * @created 10/12/2020 - 15:49
 * @project shopping-product
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductApplication.class})
@Slf4j
class ProductServiceImplTest {

    @Autowired
    ProductJpaRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @MockBean
    ProductService productService;
    @MockBean
    ProductMapper productMapper;

    @MockBean
    CategoryConverter categoryConverter;

    @Test
    void updateProductService() {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(0L);
        productDto.setPrice(0.1);
        productDto.setDescription("desc");
        productDto.setName("name");
        productDto.setUpc("upc");

        productMapper = new ProductMapperImpl();

        productRepository.save(productMapper.dto2Entity(productDto));



        //mock

        ProductService mock = new ProductServiceImpl(productRepository,productMapper,categoryRepository, categoryConverter);

        productDto.setId(1L);
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");
        //when
        System.out.println("\n\n\n\n\n"+mock.getAll()+"\n\n\n\n\n\n");

        mock.update(productDto);
        //verify
        assertEquals(mock.getProductById(productDto.getId()), productDto);

        productDto.setId(100L);

        Exception exception = assertThrows(NotFoundException.class, () -> mock.update(productDto));

        assertEquals(exception.getMessage(), "Item with id: 100 is not found!");
    }

    @Test
    void deleteProductService() {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(0L);
        productDto.setPrice(0.1);
        productDto.setDescription("desc");
        productDto.setName("name");
        productDto.setUpc("upc");

        productMapper = new ProductMapperImpl();

        productRepository.save(productMapper.dto2Entity(productDto));

        ProductService mock = new ProductServiceImpl(productRepository,productMapper,categoryRepository, categoryConverter);

        ProductDto productDto2 = mock.getProductById(2L);

        assertEquals(mock.delete(2L),productDto2);

        Exception exception = assertThrows(NotFoundException.class, () -> mock.delete(20L));

        assertEquals(exception.getMessage(), "Item with id: 20 is not found!");

    }

}