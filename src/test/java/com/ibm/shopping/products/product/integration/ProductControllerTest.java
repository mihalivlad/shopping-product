package com.ibm.shopping.products.product.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.shopping.products.product.ProductApplication;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author VladCorocea
 * @created 11/12/2020 - 09:02
 * @project shopping-product
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {ProductApplication.class}
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductJpaRepository productJpaRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void AverifyProducts() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setPrice(0.1);
        productDto.setDescription("descUU");
        productDto.setName("nameUU");
        productDto.setUpc("upcUU");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addProduct")
                .content(objectMapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println("\n\n\n\n"+mvcResult.getResponse().getContentAsString());


        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/verifyProducts")
                .content(objectMapper.writeValueAsString(ids))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        ids.remove(1L);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/verifyProducts")
                .content(objectMapper.writeValueAsString(ids))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
        System.out.println("");

    }

    @Test
    void BupdateProduct() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());

        productDto.setId(1L);
        productDto.setName("nameUpdated");
        productDto.setDescription("descriptionUpdated");
        productDto.setUpc("upcUpdated");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/updateProduct")
                .content(objectMapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(productDto.getName()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()))
                .andExpect(jsonPath("$.upc").value(productDto.getUpc()));

        productDto.setId(100L);

        resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/updateProduct")
                .content(objectMapper.writeValueAsString(productDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());

    }

    @Test
    void CdeleteProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setCategories(new ArrayList<>());
        productDto.setId(1L);
        productDto.setName("nameUpdated");
        productDto.setDescription("descriptionUpdated");
        productDto.setUpc("upcUpdated");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(productDto.getName()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()))
                .andExpect(jsonPath("$.upc").value(productDto.getUpc()));

        resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/100")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

}