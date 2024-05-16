package com.biel.bossaboxchallenge.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String msg){
        super(msg);
    }

    public IdNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
