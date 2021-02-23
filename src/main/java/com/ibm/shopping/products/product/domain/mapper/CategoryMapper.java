package com.ibm.shopping.products.product.domain.mapper;

import com.ibm.shopping.products.product.domain.dto.CategoryDto;
import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.entity.Category;
import com.ibm.shopping.products.product.domain.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author RahelaToth
 * @created 08/12/2020 - 11:34 AM
 * @project shopping-product
 */

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {

    CategoryDto entity2Dto(Category dao);
    Category dto2Entity(CategoryDto dto);
    List<CategoryDto> entityList2DtoList(List<Category> daos);
    List<Category> dtoList2EntityList(List<CategoryDto> dto);
}
