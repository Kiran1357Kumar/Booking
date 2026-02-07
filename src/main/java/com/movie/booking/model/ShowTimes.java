package com.movie.booking.model;

import java.util.List;

public class ShowTimes {
	
	private long availableSeats;
	private double price;
	private String showTime;
	
	public long getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(long availableSeats) {
		this.availableSeats = availableSeats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	
	 
}
