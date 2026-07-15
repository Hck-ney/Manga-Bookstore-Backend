package com.example.demo.product.controller;

import com.example.demo.product.entity.Product;
import com.example.demo.product.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping("/Product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(productServices.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/Product/{product_id}")
    public Product getProduct(@PathVariable Long product_id){
        return productServices.getProduct(product_id);
    }

     @GetMapping("/Product/")
    public List<Product> getAllProducts(){
        return productServices.getAllProducts();
     }

     @PutMapping("/Product/{product_id}")
    public Product updateProduct(@PathVariable Long product_id, @RequestBody Product product){
        return productServices.updateProduct(product_id, product);
     }

     @DeleteMapping("/Product/{product_id}")
    public void deleteProduct(@PathVariable Long product_id){
        productServices.deleteProduct(product_id);
     }
}
