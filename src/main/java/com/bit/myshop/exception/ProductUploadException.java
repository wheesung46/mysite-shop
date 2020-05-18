package com.bit.myshop.exception;

public class ProductUploadException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2844575395236746227L;
	
	public ProductUploadException() {
		super("Product File Upload Exception");
	}
	public ProductUploadException(String message) {
		super(message);
	}
}
