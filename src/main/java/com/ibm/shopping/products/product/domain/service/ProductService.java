package com.ibm.shopping.products.product.domain.service;

import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseShoppingServiceDTO;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseToUserDTO;

import java.util.List;

/**
 * @author RahelaToth
 * @created 04/12/2020 - 8:11 PM
 * @project shopping-product
 */

public interface ProductService {

    ProductDto update(ProductDto productDto);
    ProductDto delete(long id);
    ProductDto save(ProductDto dto);
    ProductDto getProductById(Long id);
    List<ProductDto> getAll();

    List<ProductResponseToUserDTO> getProductsByCategory(Long id);

    List<ProductResponseShoppingServiceDTO> verifyProducts(List<Long> ids);

}
