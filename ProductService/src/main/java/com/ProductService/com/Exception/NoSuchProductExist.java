package com.ProductService.com.Exception;

public class NoSuchProductExist extends RuntimeException{
    private String message;
    public NoSuchProductExist() {}
    public NoSuchProductExist(String message){
        super(message);
        this.message = message;
    }
}
