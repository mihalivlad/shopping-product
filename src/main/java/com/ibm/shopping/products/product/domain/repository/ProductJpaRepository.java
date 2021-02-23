package com.ibm.shopping.products.product.domain.repository;

import com.ibm.shopping.products.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author RahelaToth
 * @created 04/12/2020 - 7:07 PM
 * @project shopping-product
 */

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
    List<Product> findAll();

}
