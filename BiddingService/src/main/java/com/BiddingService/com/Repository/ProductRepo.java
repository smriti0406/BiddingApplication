package com.BiddingService.com.Repository;

import com.BiddingService.com.Entity.Product;
import com.BiddingService.com.Enum.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String>, CustomProductRepo{

    List<Product> findByProductType(ProductType productType);
    List<Product> findByVendor(String vendor);
    void deleteByProductId(String id);

}