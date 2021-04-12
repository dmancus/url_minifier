package com.test.URLMinifier.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RedirectFailException extends RuntimeException{
    public RedirectFailException(String errorMessage){
        super(errorMessage);
    }

    public RedirectFailException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
