package com.ProductService.com.controller;

import com.ProductService.com.Entity.Product;
import com.ProductService.com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/vendor/{user_id}"})
    public ResponseEntity<?> getProduct(@PathVariable String user_id) {
        List<Product> products = productService.getProductsByVendor(user_id);
        if(products == null) {
            return new ResponseEntity<>("No listed product exist", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable String product_id) {
        Product product = productService.getProductById(product_id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        List<Product> product = productService.getProductByCategory(category);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
