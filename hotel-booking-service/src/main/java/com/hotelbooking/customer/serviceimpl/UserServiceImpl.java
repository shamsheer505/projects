package com.hotelbooking.customer.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.customer.controller.UserController;
import com.hotelbooking.customer.dao.UserDAO;
import com.hotelbooking.customer.dto.Status;
import com.hotelbooking.customer.model.User;
import com.hotelbooking.customer.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;

	@Override
	public Map<String, Object> addBonusPointsToUser(User user) {
		
		logger.info("In service call to add bonus points to userId : "+user.getUserId());

		Map<String, Object> resultMap = null;

		if (userDAO.addBonusPointsToUser(user)) {

			resultMap = new HashMap<String, Object>();
			Status status = new Status();
			status.setStatus(true);
			status.setMessage("Added bonus points to user successfully ");
			status.setCode("200");
			resultMap.put("status", status);
		}

		return resultMap;
	}

}
