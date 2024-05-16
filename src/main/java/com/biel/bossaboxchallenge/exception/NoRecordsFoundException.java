package com.biel.bossaboxchallenge.exception;

public class NoRecordsFoundException extends RuntimeException {

    public NoRecordsFoundException(String msg){
        super(msg);
    }

    public NoRecordsFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
