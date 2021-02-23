package com.ibm.shopping.products.product.domain.mapper;

import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseToUserDTO;
import com.ibm.shopping.products.product.domain.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author RahelaToth
 * @created 07/12/2020 - 11:15 AM
 * @project shopping-product
 */

@Component
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    ProductDto entity2Dto(Product dao);
    Product dto2Entity(ProductDto dto);
    List<ProductDto> entityList2DtoList(List<Product> daos);
    List<Product> dtoList2EntityList(List<ProductDto> dto);
    List<ProductResponseToUserDTO> entityListToDTOList(List<Product> products);
}
