package com.BiddingService.com.Entity;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document
public class Bid {

    @Id
    @Field("_id")
    public String bid_id;

    public LocalDateTime placed_at;


    public String placedBy;

    @NonNull
    public String product;
    @NonNull
    public int bid_amount;

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    public LocalDateTime getPlaced_at() {
        return placed_at;
    }

    public void setPlaced_at(LocalDateTime placed_at) {
        this.placed_at = placed_at;
    }

    @NonNull
    public String getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(@NonNull String placed_by) {
        this.placedBy = placed_by;
    }

    @NonNull
    public String getProduct() {
        return product;
    }

    public void setProduct(@NonNull String product) {
        this.product = product;
    }

    public int getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(int bid_amount) {
        this.bid_amount = bid_amount;
    }
}
