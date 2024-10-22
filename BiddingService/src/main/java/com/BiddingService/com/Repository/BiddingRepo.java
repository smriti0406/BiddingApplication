package com.BiddingService.com.Repository;

import com.BiddingService.com.Entity.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BiddingRepo extends MongoRepository<Bid, String> {
    List<Bid> findByPlacedBy(String userId);
    List<Bid> findByProduct(String productId);
}

