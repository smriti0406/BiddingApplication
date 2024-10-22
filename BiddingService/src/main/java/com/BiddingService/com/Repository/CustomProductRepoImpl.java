package com.BiddingService.com.Repository;

import com.BiddingService.com.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class CustomProductRepoImpl implements CustomProductRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateProductDetails(String productId, Product product){
        Query query = new Query(Criteria.where("productId").is(productId));
        Update update = new Update();

        if(product.getName() != null){
            update.set("name", product.getName());
        }
        if(product.getDescription() != null){
            update.set("description", product.getDescription());
        }
        if(product.getImages() != null){
            update.set("images", product.getImages());
        }
        update.set("lastUpdated_at", LocalDateTime.now());
        if(product.getMax_bid_price() != 0) {
            update.set("max_bid_price", product.getMax_bid_price());
        }
        if(product.getBiddingStatus() != null){
            update.set("biddingStatus", product.getBiddingStatus());
        }
        if (product.getBase_price()!=0) {
            update.set("base_price", product.getBase_price());
        }
        if(product.getMax_bidder()!=null) {
            update.set("max_bidder", product.getMax_bidder());
        }

        mongoTemplate.updateFirst(query, update, Product.class);
    }

}
