package com.movie.booking.model;

public class PaymentRequest {
	
	private long userId;
    private long movieId;
    private String movieName;
    private int noOfTickets;
    private String paymentType;
    private String showTime;
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
