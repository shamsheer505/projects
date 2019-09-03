/**********************************************************************************
Date          	Version       Modified By       	Description  
***********		********      *************		    *************	
09-10-2017		v1.0       	  Shahid ul Islam	    Initial Version.
***********************************************************************************/

package com.hotelbooking.customer.dto;

public class Status {
	private String code;
	private String message;
	private boolean status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
