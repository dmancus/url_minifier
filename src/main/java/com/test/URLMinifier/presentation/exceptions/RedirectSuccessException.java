package com.test.URLMinifier.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FOUND)
public class RedirectSuccessException extends RuntimeException{
}
