package com.mcommerce.mexpedition.exception;

import java.io.Serial;

public class ExpeditionNotFoundException extends RuntimeException{

    /**
     * 
     */
    @Serial
    private static final long serialVersionUID = 1L;
	
	public ExpeditionNotFoundException(String message) {
        super(message);
    }

}
