package com.uiclient.microservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductBadRequestException extends RuntimeException {

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = 1L;
	
	public ProductBadRequestException(String message) {
	
	      super(message);
	
	  }

}
