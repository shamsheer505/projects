package com.hotelbooking.customer.exception;

public class InvalidRoomCategoryException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRoomCategoryException(String message) {
		super(message);
	}

}
