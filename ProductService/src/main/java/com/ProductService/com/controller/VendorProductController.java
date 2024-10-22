package com.ProductService.com.controller;

import com.ProductService.com.Entity.Product;
import com.ProductService.com.Entity.User;
import com.ProductService.com.Service.ProductService;
import com.ProductService.com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/vendor")
public class VendorProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @PostMapping("/add-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.saveProduct(product, userName);
        return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable String productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.getProductById(productId);
        User user = userService.getUser(userName);
        if(!productSaved.getVendor().equals(user.getUser_id())) {
            return new ResponseEntity<>("Action Not Allowed",HttpStatus.FORBIDDEN);
        }
        Product old = productService.updateProduct(product, productId);
        return new ResponseEntity<>(old, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String product_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.getProductById(product_id);
        User user = userService.getUser(userName);
        if(!productSaved.getVendor().equals(user.getUser_id())) {
            return new ResponseEntity<>("Action Not Allowed",HttpStatus.FORBIDDEN);
        }
        productService.deleteProduct(product_id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }


    @RequestMapping("/{product_id}/cancelBidding")
    public ResponseEntity<?> cancelProductBiddingStatus(@PathVariable String product_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.getProductById(product_id);
        User user = userService.getUser(userName);
        if(!productSaved.getVendor().equals(user.getUser_id())) {
            return new ResponseEntity<>("Action Not Allowed",HttpStatus.FORBIDDEN);
        }
        productService.updateBiddingStatusToCancelled(product_id);
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }

    @RequestMapping("/{product_id}/soldBidding")
    public ResponseEntity<?> soldProductBiddingStatus(@PathVariable String product_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.getProductById(product_id);
        User user = userService.getUser(userName);
        if(!productSaved.getVendor().equals(user.getUser_id())) {
            return new ResponseEntity<>("Action Not Allowed",HttpStatus.FORBIDDEN);
        }
        productService.updateBiddingStatusToSold(product_id);
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }

    @RequestMapping("/{product_id}/startagainBidding")
    public ResponseEntity<?> ongoingProductBiddingStatus(@PathVariable String product_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.getProductById(product_id);
        User user = userService.getUser(userName);
        if(!productSaved.getVendor().equals(user.getUser_id())) {
            return new ResponseEntity<>("Action Not Allowed", HttpStatus.FORBIDDEN);
        }
        productService.updateBiddingStatusToOngoing(product_id);
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }

    @PutMapping("/{product_id}/endBidding")
    public ResponseEntity<?> endedProductBiddingStatus(@PathVariable String product_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Product productSaved = productService.getProductById(product_id);User user = userService.getUser(userName);
        if(!productSaved.getVendor().equals(user.getUser_id())) {
            return new ResponseEntity<>("Action Not Allowed", HttpStatus.FORBIDDEN);
        }
        productService.updateBiddingStatusToEnded(product_id);
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }
}
