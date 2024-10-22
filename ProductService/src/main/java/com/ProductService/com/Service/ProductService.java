package com.ProductService.com.Service;

import com.ProductService.com.Entity.Product;
import com.ProductService.com.Entity.User;
import com.ProductService.com.Enum.BiddingStatus;
import com.ProductService.com.Enum.ProductType;
import com.ProductService.com.Exception.NoSuchProductExist;
import com.ProductService.com.Repository.ProductRepo;
import com.ProductService.com.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    private static final Object LOCK = new Object();

    public List<Product> getAllTheProduct() {
        return productRepo.findAll();
    }

    public Product saveProduct(Product product, String userName) {
            String uuidStr;
            UUID uuid = UUID.randomUUID();
            uuidStr = "product-" + uuid.toString();
            product.setProductId(uuidStr);
            product.setVendor(userName);
            product.setLastUpdated_at(LocalDateTime.now());
            product.setMax_bid_price(product.getBase_price());
            product.setBiddingStatus(BiddingStatus.ONGOING);
            User user =  userRepo.findByUsername(userName);
            product.setVendor(user.getUser_id());
            product.setProduct_type(getProductType(product));
            if(product.getBiddingFinished_at() == 0) {
                product.setBiddingFinished_at(3);
            }
            product.setMax_bidder(null);
            startBiddingTimer(product);
            return productRepo.save(product);
    }
    List<ProductType> getProductType(Product product) {
        String description = product.getDescription().toLowerCase();
        List<ProductType> productType = new ArrayList<>();

        if(description.contains("car") || description.contains("truck") || description.contains("bus") || description.contains("scooter") || description.contains("bicycle") || description.contains("bike")) {
            productType.add(ProductType.VEHICLE);
        } else if (description.contains("kitchen") || description.contains("microwave") || description.contains("induction") || description.contains("fridge") || description.contains("oven") || description.contains("cooker") || description.contains("mixer") || description.contains("grinder")) {
            productType.add(ProductType.KITCHEN_APPLIANCE);
        } else if (description.contains("suitcase") || description.contains("bag") || description.contains("travel")) {
            productType.add(ProductType.TRAVEL_UTILITY);
        } else if (description.contains("furniture") || description.contains("bed") || description.contains("sofa")|| description.contains("chair")|| description.contains("table")) {
             productType.add(ProductType.FURNITURE);
        } else if (description.contains("tv") || description.contains("washing") || description.contains("mobile") || description.contains("laptop") || description.contains("computer") || description.contains("tablet")|| description.contains("digital") || description.contains("watch")) {
             productType.add(ProductType.ELECTRONICS);
        } else if (description.contains("dumble") || description.contains("gym") || description.contains("weights") || description.contains("chest press") || description.contains("bench press")) {
            productType.add(ProductType.GYM_EQUIPMENT);
        } else {
            productType.add(ProductType.OTHER);
        }
        return productType;
    }
    public Product updateProduct(Product product, String product_id) {
        Product old = productRepo.findById(product_id).orElse(null);
        if(old == null) {
            throw new NoSuchProductExist("Product with ID"+  product_id + " does not exist");
        }
        synchronized (LOCK) {
            productRepo.updateProductDetails(product_id, product);
        }
        return productRepo.findById(product_id).orElse(null);
    }
    public Product getProductById(String productId) {
        //Product product = productRepo.findById(productId).orElse(null);
        Product product = productRepo.findByProductId(productId);
        if(product != null) {
            return product;
        }

        throw new NoSuchProductExist("Product with ID "+  productId + " do not exist");
    }


    public List<Product> getProductByCategory(String category) {
        category = category.toLowerCase();
        ProductType productType;
        if(category.contains("car") || category.contains("truck") || category.contains("bus") || category.contains("scooter") || category.contains("bicycle") || category.contains("bike")) {
            productType = ProductType.VEHICLE;
        } else if (category.contains("kitchen") || category.contains("microwave") || category.contains("induction") || category.contains("fridge") || category.contains("oven") || category.contains("cooker") || category.contains("mixer") || category.contains("grinder")) {
            productType = ProductType.KITCHEN_APPLIANCE;
        } else if (category.contains("suitcase") || category.contains("bag") || category.contains("travel")) {
            productType = ProductType.TRAVEL_UTILITY;
        } else if (category.contains("furniture") || category.contains("bed") || category.contains("sofa")|| category.contains("chair")||category.contains("table")) {
            productType = ProductType.FURNITURE;
        } else if (category.contains("tv") || category.contains("washing") || category.contains("mobile") || category.contains("laptop") || category.contains("computer") ||category.contains("tablet")||category.contains("digital") ||category.contains("watch")) {
            productType = ProductType.ELECTRONICS;
        } else if (category.contains("dumble") || category.contains("gym") || category.contains("weights") || category.contains("chest press") || category.contains("bench press")) {
            productType = ProductType.GYM_EQUIPMENT;
        } else {
            productType = ProductType.OTHER;
        }
        List<Product> products = productRepo.findByProductType(productType);
        return products;
    }

    public void deleteProduct(String productId) {
        Product product = productRepo.findById(productId).orElse(null);
        if(product != null) {
            productRepo.deleteByProductId(product.getProductId());
            return;
        }
        throw new NoSuchProductExist("Product with ID "+  productId + " do not exist");
    }

    public void updateBiddingStatusToOngoing(String product_id) {
        synchronized (LOCK) {
            Product product = productRepo.findById(product_id).orElse(null);
            if(product != null) {
                if(product.getBiddingStatus() != BiddingStatus.ONGOING && product.getBiddingStatus() != BiddingStatus.SOLD) {
                    product.setBiddingStatus(BiddingStatus.ONGOING);
                    startBiddingTimer(product);
                    productRepo.updateProductDetails(product_id, product);
                }
            }
        }

    }

    public void updateBiddingStatusToSold(String product_id) {
        synchronized (LOCK) {
            Product product = productRepo.findById(product_id).orElse(null);
            if (product != null) {
                if(product.getBiddingStatus() != BiddingStatus.CANCELLED) {
                    product.setBiddingStatus(BiddingStatus.SOLD);
                    productRepo.updateProductDetails(product_id, product);

                }
            }
        }

    }

    public void updateBiddingStatusToCancelled(String product_id) {
        synchronized (LOCK) {
            Product product = productRepo.findById(product_id).orElse(null);
            if (product != null) {
                if(product.getBiddingStatus() != BiddingStatus.SOLD) {
                    product.setBiddingStatus(BiddingStatus.CANCELLED);
                    productRepo.updateProductDetails(product_id, product);
                }
            }
        }

    }

    public void updateBiddingStatusToEnded(String product_id) {
        synchronized (LOCK) {
            Product product = productRepo.findById(product_id).orElse(null);
            if (product != null) {
                if(product.getBiddingStatus() != BiddingStatus.SOLD && product.getBiddingStatus() != BiddingStatus.CANCELLED) {
                    product.setBiddingStatus(BiddingStatus.ENDED);
                    productRepo.updateProductDetails(product_id, product);
                }
            }
        }

    }
    void startBiddingTimer(Product product) {
        Long delay = (long) (product.biddingFinished_at*60*60*1000);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               updateBiddingStatusToSold(product.getProductId());
                           }
                       }, delay);
    }


    public List<Product> getProductsByVendor(String userName) {
        List<Product> products = productRepo.findByVendor(userName);
        return products;
    }
}
