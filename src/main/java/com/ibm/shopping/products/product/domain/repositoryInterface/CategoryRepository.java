package com.ibm.shopping.products.product.domain.repositoryInterface;

import com.ibm.shopping.products.product.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 10:44 AM
 * @project shopping-product
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
