package com.hotelbooking.customer.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(annotations = RestController.class) @RequestMapping("application/vnd.error+json")
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	 public ResponseEntity<ErrorCodes> customHandleNotFound(Exception ex, WebRequest request) {

		ErrorCodes errors = new ErrorCodes();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
	@ExceptionHandler(Exception.class)
	 public ResponseEntity<ErrorCodes> generalException(Exception ex, WebRequest request) {

		ErrorCodes errors = new ErrorCodes();
       errors.setTimestamp(LocalDateTime.now());
       errors.setError("Unable to process the request now");
       errors.setStatus(HttpStatus.EXPECTATION_FAILED.value());

       return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

   }
	
	@ExceptionHandler(RoomsNotAvailableException.class)
	 public ResponseEntity<ErrorCodes> roomsNotAvailableException(Exception ex, WebRequest request) {

		ErrorCodes errors = new ErrorCodes();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError("Rooms are not available at present");
        errors.setStatus(HttpStatus.EXPECTATION_FAILED.value());

      return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

  }
	
	@ExceptionHandler(InvalidRoomCategoryException.class)
	 public ResponseEntity<ErrorCodes> invalidRoomCategoryException(Exception ex, WebRequest request) {

		ErrorCodes errors = new ErrorCodes();
       errors.setTimestamp(LocalDateTime.now());
       errors.setError("Invalid room category provided");
       errors.setStatus(HttpStatus.EXPECTATION_FAILED.value());

     return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

 }
	
	
}
