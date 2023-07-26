package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productModel) {
        var product = new ProductModel();
        BeanUtils.copyProperties(productModel, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    public ResponseEntity<ProductModel> getProductById(UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(productRepository.findById(id).orElse(null));
    }
}
