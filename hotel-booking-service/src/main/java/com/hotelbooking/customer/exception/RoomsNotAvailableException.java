package com.hotelbooking.customer.exception;

public class RoomsNotAvailableException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomsNotAvailableException(String message) {
		
		super(message);
	}

}
