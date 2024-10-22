package com.ProductService.com.Repository;

import com.ProductService.com.Entity.Product;

public interface CustomProductRepo {
    void updateProductDetails(String ProductId, Product product);
}

