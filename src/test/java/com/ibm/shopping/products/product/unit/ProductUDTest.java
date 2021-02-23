package com.ibm.shopping.products.product.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.shopping.products.product.adapter.ProductController;
import com.ibm.shopping.products.product.aop.exception.NotFoundException;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.mapper.ProductMapper;
import com.ibm.shopping.products.product.domain.mapper.ProductMapperImpl;
import com.ibm.shopping.products.product.domain.repository.ProductJpaRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.CategoryRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.ProductRepository;
import com.ibm.shopping.products.product.domain.service.CategoryService;
import com.ibm.shopping.products.product.domain.service.ProductService;
import com.ibm.shopping.products.product.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author VladCorocea
 * @created 09/12/2020 - 16:31
 * @project shopping-product
 */


@WebMvcTest
@ExtendWith(SpringExtension.class)
class ProductUDTest {

    @MockBean
    ProductJpaRepository productRepository;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    ProductMapper productMapper;

    @MockBean
    CategoryService categoryService;
    @MockBean
    ProductService productService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Test
    void updateProductController() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(1L);
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");


        when(productService.update(productDto)).thenReturn(productDto);

        mockMvc.perform(put("/updateProduct")
                .content(mapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(productDto.getName()))
                .andDo(print());
    }

    @Test
    void deleteProduct() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(1L);
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");


        when(productService.delete(productDto.getId())).thenReturn(productDto);

        mockMvc.perform(delete("/deleteProduct/{id}", productDto.getId())
                .content(mapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(productDto.getName()))
                .andDo(print());

    }
}