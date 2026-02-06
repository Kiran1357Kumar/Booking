package com.movie.booking.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitiesResponse {
	private long cityId;
    private String cityName;
    public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<LanguageResponse> getLanguages() {
		return languages;
	}
	public void setLanguages(List<LanguageResponse> languages) {
		this.languages = languages;
	}
	private List<LanguageResponse> languages;
   
    
}
