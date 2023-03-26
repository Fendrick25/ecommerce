package com.ecommerce.product.controller;

import com.ecommerce.Data;
import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.dto.CreateProduct;
import com.ecommerce.product.dto.FindProductResponse;
import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Data<ProductDto>> getProduct(@PathVariable("productId") UUID productId){
        return new ResponseEntity<>(new Data<>(productService.getProduct(productId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Data<ProductDto>> createProduct(@Valid @RequestBody CreateProduct createProduct){
        return new ResponseEntity<>(new Data<>(productService.createProduct(createProduct)), HttpStatus.CREATED);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Data<FindProductResponse>> findAllProduct(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(new Data<>(productService.findAllProductSortByTotalSold(page, size)), HttpStatus.OK);
    }

    @GetMapping(params = {"name", "page", "size"})
    public ResponseEntity<Data<FindProductResponse>> findProductByName(@RequestParam String name, @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(new Data<>(productService.findProductByName(name, page, size)), HttpStatus.OK);
    }

    @GetMapping(params = {"category", "page", "size"})
    public ResponseEntity<Data<FindProductResponse>> findProductByCategory(@RequestParam ProductCategory category, @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(new Data<>(productService.findProductByCategory(category, page, size)), HttpStatus.OK);
    }

    @GetMapping(params = {"name", "category", "page", "size"})
    public ResponseEntity<Data<FindProductResponse>> findProductByNameAndCategory(@RequestParam String name, @RequestParam ProductCategory category, @RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(new Data<>(productService.findProductByNameAndCategory(name, category, page, size)), HttpStatus.OK);
    }
}
