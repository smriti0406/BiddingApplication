package com.ProductService.com.Exception;

public class ProductAlreadyExistException extends RuntimeException {
    private String message;
    public ProductAlreadyExistException() {}
    public ProductAlreadyExistException(String  message) {
        super(message);
        this.message = message;
    }
}
