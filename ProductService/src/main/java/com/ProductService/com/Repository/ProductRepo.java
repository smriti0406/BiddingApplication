package com.ProductService.com.Repository;

import com.ProductService.com.Entity.Product;
import com.ProductService.com.Enum.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String>, CustomProductRepo {

    List<Product> findByProductType(ProductType productType);
    List<Product> findByVendor(String vendor);
    Product findByProductId(String productId);
    void deleteByProductId(String id);

}
