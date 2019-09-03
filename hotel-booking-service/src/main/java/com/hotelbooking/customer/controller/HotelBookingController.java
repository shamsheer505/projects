package com.hotelbooking.customer.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.customer.dto.APIResponse;
import com.hotelbooking.customer.dto.APIResponse.ResponseCode;
import com.hotelbooking.customer.dto.Status;
import com.hotelbooking.customer.model.User;
import com.hotelbooking.customer.service.HotelBookingService;

@RestController
@RequestMapping("/hotelbookingservice")
public class HotelBookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelBookingController.class);

	@Autowired
	HotelBookingService hotelBookingService;
	
	/**
	 * @author Shamsheer
	 * @param user
	 * @return APIResponse
	 * @throws Exception
	 * This is an API request to book hotel rooms with bonus points available for an User.
	 */

	@RequestMapping(value = "/api/rooms", method = RequestMethod.POST, consumes = "application/json")
	public APIResponse<Object> bookHotelRoom(@RequestBody User user) throws Exception {

		logger.info("hotel room booking controller method start : userId "+user.getUserId());
		Map<String, Object> finalData = null;
		APIResponse<Object> response = new APIResponse<Object>();
		Status status = null;
			finalData = hotelBookingService.bookHotelRoom(user);
			status = (Status) finalData.get("status");
			if (status.isStatus()) {
				response.setCode(ResponseCode.SUCCESS.toString());
			}else {
				response.setCode(ResponseCode.ERROR.toString());
			}
			response.setData(finalData.get("data"));
			response.setStatus(status.getCode());
			response.setStatusMessage(status.getMessage());

		return response;
	}

}
