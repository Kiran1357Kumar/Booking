package com.movie.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//@Data
@Entity
@Table(name = "CITY")
public class City {
	
	public City() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

    @Column(name = "city_name", nullable = false, length = 100)
    private String cityName;
	
   
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
