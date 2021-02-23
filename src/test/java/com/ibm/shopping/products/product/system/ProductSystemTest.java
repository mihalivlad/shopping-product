package com.ibm.shopping.products.product.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.shopping.products.product.ProductApplication;
import com.ibm.shopping.products.product.adapter.ProductController;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.dto.response.CategoryResponseDTO;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseShoppingServiceDTO;
import com.ibm.shopping.products.product.domain.mapper.CategoryMapper;
import com.ibm.shopping.products.product.domain.mapper.ProductMapper;
import com.ibm.shopping.products.product.domain.mapper.ProductMapperImpl;
import com.ibm.shopping.products.product.domain.repository.ProductJpaRepository;
import com.ibm.shopping.products.product.domain.service.CategoryService;
import com.ibm.shopping.products.product.domain.service.ProductService;
import com.ibm.shopping.products.product.domain.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author RahelaToth
 * @created 11/12/2020 - 10:59 AM
 * @project shopping-product
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProductApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductSystemTest {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void verifyProducts() throws Exception {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);

        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.post("/verifyProducts")
               .content(objectMapper.writeValueAsString(list))
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult1.getResponse().getContentAsString());
    }

    @Test
    void retrieveCategories() throws Exception{
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(2L);
        categoryResponseDTO.setName("nameCategory2");
        categoryResponseDTO.setDescription("descCategory2");
        categoryResponseDTO.setProducts(new ArrayList<>());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/retrieveCategories")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println("\n\n\n " + "++++ in getALL" + mvcResult.getResponse().getContentAsString() + "\n\n");
    }

}