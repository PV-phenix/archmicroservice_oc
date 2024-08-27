package com.mcommerce.mexpedition.exception;

public class ExpeditionNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExpeditionNotFoundException(String message) {
        super(message);
    }

}
