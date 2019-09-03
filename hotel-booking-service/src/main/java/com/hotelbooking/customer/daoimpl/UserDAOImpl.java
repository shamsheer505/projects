package com.hotelbooking.customer.daoimpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hotelbooking.customer.dao.UserDAO;
import com.hotelbooking.customer.entity.UserEntity;
import com.hotelbooking.customer.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	List<UserEntity> userEntities = Arrays.asList(new UserEntity(1L, "sham", "123", 8000),
			new UserEntity(1L, "sham", "122", 9000), new UserEntity(1L, "sham", "145", 10000));

	@Override
	public UserEntity fetchUserBonusPointsBalance(String userId) {

		// We can use actual Database connection to retrieve user details. Here I have
		// taken static values.

		try {
			logger.info("In DAO fetching bonus points info for userId : " + userId);

			List<UserEntity> user = userEntities.stream()
					.filter(p -> p.getUserId() != null && p.getUserId().equalsIgnoreCase(userId))
					.collect(Collectors.toList());

			if (user != null && user.size() > 0) {
				logger.info("In DAO fetched bonus points info of userId : " + userId);
				return user.get(0);
			}
		} catch (Exception e) {
			logger.error("Unable to fetch bonus points info of userId : " + userId);
		}

		return null;
	}

	@Override
	public boolean debitBonusPointsFromUser(String userId, int points) {

		logger.info("In DAO to debit bonus points to userId : " + userId);

		userEntities.stream().filter(p -> p.getUserId() != null && p.getUserId().equalsIgnoreCase(userId))
				.forEach(p -> {

					/**
					 * Here we can track User bonus points transactions by comparing the current
					 * bonus points information.
					 * 
					 */
					logger.info("Current bonus points for the userId : " + userId + " : " + p.getBonusPointsBalance());
					p.setBonusPointsBalance(p.getBonusPointsBalance() - points);
					logger.info("Bonus points balance after debiting the points for userId : " + userId + " : "
							+ p.getBonusPointsBalance());
				});

		logger.info("In DAO debited bonus points from userId : " + userId);

		return true;
	}

	@Override
	public boolean addBonusPointsToUser(User user) {

		logger.info("In DAO to add bonus points to userId : " + user.getUserId());

		userEntities.stream().filter(p -> p.getUserId() != null && p.getUserId().equalsIgnoreCase(user.getUserId()))
				.forEach(p -> {

					/**
					 * Here we can track User bonus points transactions by comparing the current
					 * bonus points information.
					 * 
					 */
					logger.info("Current bonus points for the userId : " + user.getUserId() + " : "
							+ p.getBonusPointsBalance());
					p.setBonusPointsBalance(p.getBonusPointsBalance() + user.getNumberOfPoints());
					logger.info("Bonus points balance after adding the points for userId : " + user.getUserId() + " : "
							+ p.getBonusPointsBalance());
				});

		logger.info("In DAO added bonus points to userId successfully : " + user.getUserId());

		return true;

	}

}
