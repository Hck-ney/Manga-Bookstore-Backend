package com.example.demo.product.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        if(product.getName() == null || product.getDescription() == null || product.getPrice() == null || product.getSku() == null){
            throw new OrderException("Missing required field",HttpStatus.BAD_REQUEST);
        }
        return productRepository.save(product);
    }

    public Product getProduct(Long product_id){
        return productRepository.findById(product_id).orElseThrow(()-> new OrderException("Product associated with this Id is not found", HttpStatus.NOT_FOUND));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(Long product_id, Product product){
        Product newProduct = productRepository.findById(product_id).orElseThrow(()-> new OrderException("Product associated with this Id is not found", HttpStatus.NOT_FOUND));
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setSku(product.getSku());

        return productRepository.save(newProduct);
    }

    public void deleteProduct(Long product_id){
        Product product = productRepository.findById(product_id).orElseThrow(()-> new OrderException("Product associated with this Id is not found", HttpStatus.NOT_FOUND));
        productRepository.delete(product);
    }
}
