package com.mcommerce.mexpedition.exception;

public class ExpeditionException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ExpeditionError {
		KeyError("Page non trouvée", 404);

        private final String message;
        private final int code;

        ExpeditionError(String message, int code) {
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

    private ExpeditionError error;
    private String message;

    public ExpeditionException(ExpeditionError error, String message) {
        this.error = error;
        this.message = message;
    }

    public ExpeditionError getError() {
        return error;
    }

    public void setError(ExpeditionError error) {
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
