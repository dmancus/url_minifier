package com.test.URLMinifier.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class ShortUrlAlreadyExistsException extends RuntimeException {
    public ShortUrlAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }

    public ShortUrlAlreadyExistsException(String errorMessage, Throwable err ){
        super(errorMessage, err);
    }

}
