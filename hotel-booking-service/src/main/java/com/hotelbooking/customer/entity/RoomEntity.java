package com.hotelbooking.customer.entity;

public class RoomEntity {

	private String roomCategory;

	private int price;

	private int availableRooms;

	public int getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}

	public RoomEntity(String roomCategory, int price, int availableRooms) {
		super();
		this.roomCategory = roomCategory;
		this.price = price;
		this.availableRooms = availableRooms;
	}

	public String getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
