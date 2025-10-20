package com.rodrigo134.products_api.controller;


import com.rodrigo134.products_api.dto.ProductResponseDto;
import com.rodrigo134.products_api.dto.RequestDto;

import com.rodrigo134.products_api.model.ProductModel;
import com.rodrigo134.products_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProductResponseDto> createProduct( @Valid @RequestBody RequestDto requestDto) {
        ProductResponseDto productDto = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }


    @GetMapping("/produtos")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto productDto = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }


    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct( @PathVariable Long id, @RequestBody RequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, requestDto));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}