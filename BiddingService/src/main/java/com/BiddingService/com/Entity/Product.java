package com.BiddingService.com.Entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.net.URL;
import java.time.LocalDateTime;


import com.BiddingService.com.Enum.BiddingStatus;
import com.BiddingService.com.Enum.ProductType;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document
public class Product {

    @Id
    @Field("_id")
    public String productId;
    @NonNull
    public String name;
    @NonNull
    public String description;
    public List<URL> images;
    @NonNull
    public int base_price;
    public String vendor;
    public BiddingStatus biddingStatus;
    public LocalDateTime lastUpdated_at;
    public int biddingFinished_at;
    public int max_bid_price;
    @Indexed
    public List<ProductType> productType;
    public String max_bidder;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String product_id) {
        this.productId = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBase_price() {
        return base_price;
    }

    public void setBase_price(int base_price) {
        this.base_price = base_price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor_id) {
        this.vendor = vendor_id;
    }

    public int getMax_bid_price() {
        return max_bid_price;
    }

    public void setMax_bid_price(int bid_price) {
        this.max_bid_price = bid_price;
    }

    public LocalDateTime getLastUpdated_at() {
        return lastUpdated_at;
    }

    public void setLastUpdated_at(LocalDateTime lastUpdated_at) {
        this.lastUpdated_at = lastUpdated_at;
    }

    public int getBiddingFinished_at() {
        return biddingFinished_at;
    }

    public void setBiddingFinished_at(int biddingFinished_at) {
        this.biddingFinished_at = biddingFinished_at;
    }

    public BiddingStatus getBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(BiddingStatus biddingStatus) {
        this.biddingStatus = biddingStatus ;
    }

    public List<URL> getImages() {
        return images;
    }

    public void setImages(List<URL> images) {
        this.images = images;
    }
    public List<ProductType> getProduct_type() {
        return productType;
    }

    public void setProduct_type(List<ProductType> product_type) {
        this.productType = product_type;
    }

    public String getMax_bidder() {
        return max_bidder;
    }

    public void setMax_bidder(String max_bidder) {
        this.max_bidder = max_bidder;
    }
}
