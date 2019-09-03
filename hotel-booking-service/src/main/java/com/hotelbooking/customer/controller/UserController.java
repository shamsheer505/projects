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
import com.hotelbooking.customer.dto.Status;
import com.hotelbooking.customer.dto.APIResponse.ResponseCode;
import com.hotelbooking.customer.model.User;
import com.hotelbooking.customer.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	/**
	 * @author Shamsheer
	 * @param user
	 * @return APIResponse
	 * This is an API Request to add bonus points to an User.
	 */

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	public APIResponse<Object> addBonusPointsToUser(@RequestBody User user) {

		Map<String, Object> finalData = null;
		APIResponse<Object> response = new APIResponse<Object>();
		Status status = null;

			logger.info("API called to add bonus points for the userId : "+user.getUserId());
			finalData = userService.addBonusPointsToUser(user);
			status = (Status) finalData.get("status");
			if (status.isStatus()) {
				response.setCode(ResponseCode.SUCCESS.toString());
			} else {
				response.setCode(ResponseCode.ERROR.toString());
			}
			response.setData(finalData.get("data"));
			response.setStatus(status.getCode());
			response.setStatusMessage(status.getMessage()); 
	
		return response;
	}

}
