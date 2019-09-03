package com.hotelbooking.customer.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.customer.dao.HotelBookingDAO;
import com.hotelbooking.customer.dao.UserDAO;
import com.hotelbooking.customer.dto.Status;
import com.hotelbooking.customer.entity.RoomEntity;
import com.hotelbooking.customer.entity.UserEntity;
import com.hotelbooking.customer.exception.InvalidRoomCategoryException;
import com.hotelbooking.customer.exception.RoomsNotAvailableException;
import com.hotelbooking.customer.model.User;
import com.hotelbooking.customer.service.HotelBookingService;

@Service
public class HotelBookingServiceImpl implements HotelBookingService {

	private static final Logger logger = LoggerFactory.getLogger(HotelBookingServiceImpl.class);

	@Autowired
	HotelBookingDAO hotelBookingDAO;

	@Autowired
	UserDAO userDAO;

	@Override
	public UserEntity fetchUserBonusPointsBalance(User user) {

		UserEntity userEntity = userDAO.fetchUserBonusPointsBalance(user.getUserId());

		return userEntity;
	}

	private RoomEntity fetchRoomDetails(User user) {

		RoomEntity roomEntity = hotelBookingDAO.fetchRoomDetails(user.getRoomCategory());

		if (roomEntity != null) {

			if (user.getNumberOfRooms() > roomEntity.getAvailableRooms()) {

				logger.info("Rooms are not available at present for the request from userId : " + user.getUserId());

				throw new RoomsNotAvailableException("Rooms are not available at present");

			}
		}

		return roomEntity;
	}

	@Override
	public Map<String, Object> bookHotelRoom(User user) {

		Map<String, Object> resultMap = new HashMap<>();

		UserEntity entity = fetchUserBonusPointsBalance(user);

		if (entity != null) {

			RoomEntity roomEntity = fetchRoomDetails(user);

			if (roomEntity == null) {
				logger.info("Invalid room category provided by the userId : " + user.getUserId());
				throw new InvalidRoomCategoryException("Invalid room category");
			}

			// calculate price based on bonus points
			int number_of_points_per_room = calculatePoints(roomEntity.getPrice(), entity.getBonusPointsBalance());

			if (number_of_points_per_room > 0) {
				/**
				 * Here we can do declarative transaction in case we are using actual database
				 * by using @Transactional annotation.
				 * 
				 */

				userDAO.debitBonusPointsFromUser(user.getUserId(), number_of_points_per_room);
				hotelBookingDAO.bookHotelRoom(user.getRoomCategory(), user.getNumberOfRooms());

				int booking_id = (int) (Math.random() * 100);

				logger.info("Room(s) booked for the request from userId : " + user.getUserId());
				Status status = new Status();
				status.setStatus(true);
				status.setMessage("Hotel room(s) booked successfully with Booking ID : "+booking_id);
				status.setCode("200");
				resultMap.put("status", status);

			} else {
				logger.info("insufficient bonus points to book room(s) for userId : " + user.getUserId());
				Status status = new Status();
				status.setStatus(true);
				status.setMessage("Insufficient bonus points to book the room");
				status.setCode("414");
				resultMap.put("status", status);
			}
		} else {

			logger.info("user details not found for userId : " + user.getUserId());
			Status status = new Status();
			status.setStatus(true);
			status.setMessage("User Details not found");
			status.setCode("404");
			resultMap.put("status", status);

		}

		return resultMap;
	}

	private int calculatePoints(int price, int points) {
		// 4 points = 1 rupee
		int priceFromPoints = points / 4;
		if (priceFromPoints >= price) {

			int number_of_points_per_room = price * 4;
			return number_of_points_per_room;
		} else {
			return -1;
		}
	}
}
