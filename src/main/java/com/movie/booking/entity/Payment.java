package com.movie.booking.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAYMENT")
public class Payment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_name", nullable = false, length = 100)
    private String userName;
	
	@Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;
	
	@Column(name = "tickets_booked")
	private Long ticketsBooked;
	
	@Column(name = "movie_name", nullable = false, length = 200)
    private String movieName;
	
	@Column(name = "payment_type", nullable = false, length = 10)
    private String paymentType;
	
	@Column(name = "amount_credited", nullable = false, length = 10)
    private double amountCredited;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getTicketsBooked() {
		return ticketsBooked;
	}

	public void setTicketsBooked(Long ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getAmountCredited() {
		return amountCredited;
	}

	public void setAmountCredited(double amountCredited) {
		this.amountCredited = amountCredited;
	}
	
	
}
