package com.movie.booking.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
	
	
	@NotNull(message = "userId is required")
	@Min(value = 1, message = "userId must be at least 1")
    @Max(value = 10, message = "userId cannot exceed 10")
	private long userId;
	
	@NotNull(message = "movieId is required")
	@Min(value = 1, message = "movieId must be at least 1")
    @Max(value = 10, message = "movieId cannot exceed 10")
    private long movieId;
    
    @NotBlank(message = "movieName should not empty or null")
    private String movieName;
    
    @NotNull(message = "noOfTickets is required")
	@Min(value = 1, message = "noOfTickets must be greater than 1")
    @Max(value = 10, message = "noOfTickets cannot exceed 10")
    private int noOfTickets;
    
    @NotBlank(message = "paymentType should not empty or null")
    private String paymentType;
    
    @NotBlank(message = "showTime should not empty or null")
    private String showTime;
    
    @NotBlank(message = "movieTheaterCode should not empty or null")
    private String movieTheaterCode;
    
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getMovieTheaterCode() {
		return movieTheaterCode;
	}
	public void setMovieTheaterCode(String movieTheaterCode) {
		this.movieTheaterCode = movieTheaterCode;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
    
    
}
