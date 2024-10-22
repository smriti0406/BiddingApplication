package com.BiddingService.com.Repository;

import com.BiddingService.com.Entity.Product;

public interface CustomProductRepo {
    void updateProductDetails(String ProductId, Product product);
}