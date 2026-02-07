package com.movie.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MOVIES")
public class Movies {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;

	@Column(name = "city_id")
    private Long cityId;
	
	@Column(name = "language_id")
    private Long languageId;
	
    @Column(name = "movie_name", nullable = false, length = 100)
    private String movieName;
    
    @Column(name = "movie_duration")
    private String movieDuration;
    
    @Column(name = "movie_rating")
    private String movieRating;
    
    @Column(name = "movie_theater")
    private String movieTheater;
    
    @Column(name = "movie_theater_code")
    private String movieTheaterCode;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "area_code")
    private String areaCode;



	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

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

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(String movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public String getMovieTheater() {
		return movieTheater;
	}

	public void setMovieTheater(String movieTheater) {
		this.movieTheater = movieTheater;
	}

	public String getMovieTheaterCode() {
		return movieTheaterCode;
	}

	public void setMovieTheaterCode(String movieTheaterCode) {
		this.movieTheaterCode = movieTheaterCode;
	}
    
    
}
