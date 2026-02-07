package com.movie.booking.service;

import java.util.List;

import com.movie.booking.entity.Movies;
import com.movie.booking.model.CitiesResponse;


public interface BookingService {

	List<CitiesResponse> getCitiesAndLanguages();

	List<Movies> getTheatersList(String cityId, String languageId);

}
