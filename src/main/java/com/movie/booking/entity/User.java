package com.movie.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name = "user_name", nullable = false, length = 100)
    private String userName;
	
	@Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;
	
	@Column(name = "address", nullable = false, length = 200)
    private String address;
	
	@Column(name = "account_balance", nullable = false, length = 10)
    private String accountBalance;
	
}
