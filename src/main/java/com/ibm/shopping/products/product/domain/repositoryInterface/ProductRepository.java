package com.ibm.shopping.products.product.domain.repositoryInterface;

import com.ibm.shopping.products.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
