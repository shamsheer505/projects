package com.hotelbooking.customer.service;

import java.util.Map;

import com.hotelbooking.customer.model.User;

public interface UserService {

	Map<String, Object> addBonusPointsToUser(User user);

}
