package com.ibm.shopping.products.product.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.shopping.products.product.ProductApplication;
import com.ibm.shopping.products.product.adapter.ProductController;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.repository.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author RahelaToth
 * @created 10/12/2020 - 2:49 PM
 * @project shopping-product
 */


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = ProductApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductCRIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductJpaRepository productJpaRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void saveProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setPrice(0.1);
        productDto.setDescription("Description1");
        productDto.setName("Name1");
        productDto.setUpc("a");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
                .content(objectMapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println("\n\n" + mvcResult.getResponse().getContentAsString() + "\n\n");

    }

    @Test
    void getProductById() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setPrice(0.2);
        productDto.setDescription("Description2");
        productDto.setName("Name2");
        productDto.setUpc("b");


        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.get("/getProduct/1")
                .content(objectMapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println("\n\n\n " + mvcResult2.getResponse().getContentAsString() + "\n\n");

    }

    @Test
    void getAllProducts() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setPrice(0.2);
        productDto.setDescription("Description2");
        productDto.setName("Name2");
        productDto.setUpc("b");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllProducts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println("\n\n\n " + "++++in getALL" + mvcResult.getResponse().getContentAsString() + "\n\n");
    }
}