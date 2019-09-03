/**********************************************************************************
Date          	Version       Modified By       	Description  
***********		********      *************		    *************	
09-10-2017		v1.0       	  Shahid ul Islam	    Initial Version.
***********************************************************************************/

package com.hotelbooking.customer.dto;

public class APIResponse<T> {

	public enum ResponseCode {
		SUCCESS {
		    public String toString() {
		        return "200";
		    }
		},
 
		CREATED {
		    public String toString() {
		        return "201";
		    }
		},
		NOT_MODIFIED {
		    public String toString() {
		        return "304";
		    }
		},
		ERROR {
		    public String toString() {
		        return "500";
		    }
		},
		UNAUTHORIZED {
		    public String toString() {
		        return "401";
		    }
		},
		PRECONDITION_FAILED {
		    public String toString() {
		        return "412";
		    }
		},
		BAD_REQUEST {
			public String toString() {
		        return "400";
		    }
		},
		FORBIDDEN {
			public String toString() {
		        return "403";
		    }
		},
		NOT_FOUND {
			public String toString() {
		        return "404";
		    }
		},
		DELETED {
			public String toString() {
		        return "204";
		    }
		}
		
		}
	
	private String code;
	private String status;
	private String statusMessage;
	private T data;
	public APIResponse(){}
	
	public APIResponse(String code, String status, String statusMessage, T data) {
		super();
		this.code = code;
		this.status = status;
		this.statusMessage = statusMessage;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
