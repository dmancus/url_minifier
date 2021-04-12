package com.test.URLMinifier.service.exceptions;

public class NoSuchMapEntryException extends RuntimeException{
    public NoSuchMapEntryException(String errorMessage){
        super(errorMessage);
    }

    public NoSuchMapEntryException(String errorMessage, Throwable err){
        super(errorMessage,err);
    }
}
