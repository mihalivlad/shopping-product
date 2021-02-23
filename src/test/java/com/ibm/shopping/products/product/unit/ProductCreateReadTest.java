package com.ibm.shopping.products.product.unit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.shopping.products.product.adapter.ProductController;
import com.ibm.shopping.products.product.aop.exception.NotFoundException;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.mapper.CategoryMapper;
import com.ibm.shopping.products.product.domain.mapper.ProductMapper;
import com.ibm.shopping.products.product.domain.mapper.ProductMapperImpl;
import com.ibm.shopping.products.product.domain.mapper.converter.CategoryConverter;
import com.ibm.shopping.products.product.domain.repository.ProductJpaRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.CategoryRepository;
import com.ibm.shopping.products.product.domain.repositoryInterface.ProductRepository;
import com.ibm.shopping.products.product.domain.service.CategoryService;
import com.ibm.shopping.products.product.domain.service.ProductService;
import com.ibm.shopping.products.product.domain.service.impl.ProductServiceImpl;
import org.aspectj.lang.annotation.Before;
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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author RahelaToth
 * @created 10/12/2020 - 2:18 PM
 * @project shopping-product
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductCreateReadTest {


    @MockBean
    ProductJpaRepository productRepository;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    ProductMapper productMapper;

    @MockBean
    CategoryMapper categoryMapper;

    @MockBean
    CategoryConverter categoryConverter;

    @MockBean
    CategoryService categoryService;
    @MockBean
    ProductService productService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void init(){

        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(1L);
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");

        productService.save(productDto);


    }


    @Test
    void saveProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(1L);
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");


        when(productService.save(productDto)).thenReturn(productDto);

        mockMvc.perform(post("/addProduct")
                .content(mapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productDto.getId()))
                .andExpect(jsonPath("$.price").value(productDto.getPrice()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()))
                .andExpect(jsonPath("$.name").value(productDto.getName()))
                .andExpect(jsonPath("$.upc").value(productDto.getUpc()))
                .andExpect(jsonPath("$.categories").value(productDto.getCategories()))
                .andDo(print());
    }

    @Test
    void getProductById() {
    }

    @Test
    void getAllProducts() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(1L);
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");

        List<ProductDto> list = new ArrayList<>();
        list.add(productDto);


        when(productService.getAll()).thenReturn(list);

        mockMvc.perform(get("/getAllProducts")
                .content(mapper.writeValueAsString(list))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

}