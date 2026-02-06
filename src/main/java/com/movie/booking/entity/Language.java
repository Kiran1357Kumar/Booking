package com.movie.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LANGUAGE")
public class Language {
	
	public Language() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

    public Language(Long cityId, String languageName) {
		this.cityId = cityId;
		this.languageName = languageName;
	}

	@Column(name = "city_id")
    private Long cityId;

    @Column(name = "language_name", nullable = false, length = 100)
    private String languageName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
    
    
}
