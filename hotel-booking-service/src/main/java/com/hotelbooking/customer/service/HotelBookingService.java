package com.hotelbooking.customer.service;

import java.util.Map;

import com.hotelbooking.customer.entity.UserEntity;
import com.hotelbooking.customer.model.User;

public interface HotelBookingService {
	
	public UserEntity fetchUserBonusPointsBalance(User user);
	
	public Map<String, Object> bookHotelRoom(User user); 
	
	
}
