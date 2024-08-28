package com.mproduits.web.exceptions;

public class ProductException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3770133211815252626L;
	public enum HelloError {
        HELLO_NOT_FOUND("Page non trouvée", 404);

        private final String message;
        private final int code;

        HelloError(String message, int code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public int getCode() {
            return code;
        }
    }

    private HelloError error;
    private String message;

    public ProductException(HelloError error, String message) {
        this.error = error;
        this.message = message;
    }

    public HelloError getError() {
        return error;
    }

    public void setError(HelloError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
