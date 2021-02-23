package com.ibm.shopping.products.product.adapter;

import com.ibm.shopping.products.product.domain.dto.CategoryDto;
import com.ibm.shopping.products.product.domain.dto.request.CategoryRegistrationDTO;
import com.ibm.shopping.products.product.domain.dto.request.CategoryUpdateDTO;
import com.ibm.shopping.products.product.domain.dto.response.CategoryResponseDTO;
import com.ibm.shopping.products.product.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author DariusScridon
 * @created 06/12/2020 - 1:01 PM
 * @project shopping-product
 */

@RestController
@RequestMapping(value = "/category", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRegistrationDTO request) {
        CategoryResponseDTO categoryResponseDTO = categoryService.save(request);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CategoryResponseDTO>> retrieveAll() {
        Set<CategoryResponseDTO> categories = categoryService
                    .getAll()
                    .stream()
                    .map(category -> new CategoryResponseDTO(category.getId(), category.getName(), category.getDescription(), category.getProducts()))
                    .collect(Collectors.toSet());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveByName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryResponseDTO> retrieveByName(@RequestParam String name) {
        CategoryResponseDTO categoryResponseDTO = categoryService.retrieveByName(name);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveById/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryResponseDTO> retrieveById(@PathVariable Long id) {
        CategoryResponseDTO categoryResponseDTO = categoryService.retrieveById(id);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean result = categoryService.delete(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryResponseDTO> update(@RequestBody CategoryUpdateDTO categoryUpdate, @PathVariable Long id) {
        CategoryResponseDTO categoryResponseDTO =  categoryService.update(categoryUpdate, id);

        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveCategories", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CategoryDto>> retrieveCategories() {
        Set<CategoryDto> categories = categoryService
                .getAll()
                .stream()
                .map(category -> new CategoryDto(category.getId(), category.getName(), category.getDescription()))
                .collect(Collectors.toSet());

        return !categories.isEmpty()
                ? new ResponseEntity<>(categories, HttpStatus.OK)
                : new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
    }
}
