package com.hotelbooking.customer.dao;

import com.hotelbooking.customer.entity.UserEntity;
import com.hotelbooking.customer.model.User;

public interface UserDAO {

	public UserEntity fetchUserBonusPointsBalance(String userId);
	
	//public boolean debitBonusPointsFromUser(String userId);

	boolean debitBonusPointsFromUser(String userId, int points);

	public boolean addBonusPointsToUser(User user);
	
}
