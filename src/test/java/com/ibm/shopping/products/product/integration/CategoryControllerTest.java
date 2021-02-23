package com.ibm.shopping.products.product.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.shopping.products.product.ProductApplication;
import com.ibm.shopping.products.product.adapter.CategoryController;
import com.ibm.shopping.products.product.domain.dto.request.CategoryRegistrationDTO;
import com.ibm.shopping.products.product.domain.repositoryInterface.CategoryRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author DariusScridon
 * @created 11/12/2020 - 11:44 AM
 * @project shopping-product
 */


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {ProductApplication.class}
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CategoryRepository categoryRepositoryRepository;

    @Autowired
    CategoryController categoryController;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void create() throws Exception {
        CategoryRegistrationDTO categoryRegistrationDTO = new CategoryRegistrationDTO("testN1", "testD1");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/category/create")
                .content(objectMapper.writeValueAsString(categoryRegistrationDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();
    }

    @Test
    void retrieveAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/category/retrieveAll")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();
    }

    @Test
    void retrieveByName() throws Exception{
        String foundName = "testN1";
        String notFoundName = "tet";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/category/retrieveByName")
                .param("name", foundName)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/category/retrieveByName")
                .param("name", notFoundName)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void retrieveById() throws Exception {
        Integer validId = 1;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/category/retrieveById/" + validId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();

        Integer invalidId = 3;
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/category/retrieveById/" + invalidId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNotFound());



    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}