package com.ibm.shopping.products.product.adapter;

import com.ibm.shopping.products.product.domain.dto.ProductDto;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseShoppingServiceDTO;
import com.ibm.shopping.products.product.domain.dto.response.ProductResponseToUserDTO;
import com.ibm.shopping.products.product.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author RahelaToth
 * @created 04/12/2020 - 8:12 PM
 * @project shopping-product
 */

@RestController
@Slf4j
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        ProductDto productDto = productService.getProductById(id);
        log.info("--------in GetProduct method controller");
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAll();

        if (!productDtos.isEmpty()) {
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productDtos, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/verifyProducts")
    public ResponseEntity<List<ProductResponseShoppingServiceDTO>> verifyProducts(@RequestBody List<Long> ids) {
        List<ProductResponseShoppingServiceDTO> list = productService.verifyProducts(ids);
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
    }

    @PutMapping ("updateProduct")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.update(productDto), HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveProductsByCategory/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponseToUserDTO>> retrieveProductsByCategory(@PathVariable Long id) {
        List<ProductResponseToUserDTO> products = productService.getProductsByCategory(id);

        return !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
    }
}
