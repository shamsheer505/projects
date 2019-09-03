package com.hotelbooking.customer.dao;

import com.hotelbooking.customer.entity.RoomEntity;

public interface HotelBookingDAO{
	
	public RoomEntity fetchRoomDetails(String roomCategory);
	
	public boolean bookHotelRoom(String roomCategory, int numberOfRooms);

}
