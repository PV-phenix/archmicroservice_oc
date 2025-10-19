package com.mpaiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class PaiementExistantException extends RuntimeException {

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = 1L;

	public PaiementExistantException(String message) {
        super(message);
    }
}
