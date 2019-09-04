package com.hotelbooking.customer.daoimpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hotelbooking.customer.dao.HotelBookingDAO;
import com.hotelbooking.customer.entity.RoomEntity;

@Repository
public class HotelBookingDAOImpl implements HotelBookingDAO {

	private static final Logger logger = LoggerFactory.getLogger(HotelBookingDAOImpl.class);
	
	List<RoomEntity> roomEntities = Arrays.asList(new RoomEntity("Single Room", 1000, 30),
			new RoomEntity("Double Room", 1500, 30), new RoomEntity("Single Room with AC", 2000, 20));

	@Override
	public RoomEntity fetchRoomDetails(String roomCategory) {

		/**
		 * Connect to database to retrieve rooms information. I have taken static data
		 * here
		 */
		try {
		List<RoomEntity> roomEntity = roomEntities.stream()
				.filter(p -> p.getRoomCategory() != null && p.getRoomCategory().equalsIgnoreCase(roomCategory))
				.collect(Collectors.toList());

		if (roomEntity != null && roomEntity.size() > 0) {
			return roomEntity.get(0);
		}
		}catch(Exception e) {
			logger.error("Error while fetching hotel room details"+e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public boolean bookHotelRoom(String roomCategory, int numberOfRooms) {
		try {
		roomEntities.stream()
				.filter(p -> p.getRoomCategory() != null && p.getRoomCategory().equalsIgnoreCase(roomCategory))
				.forEach(p -> p.setAvailableRooms(p.getAvailableRooms() - numberOfRooms));
		}catch(Exception e) {
			logger.error("Error while booking hotel room" + e.getMessage());
			return false;
		}

		return true;
	}

}
