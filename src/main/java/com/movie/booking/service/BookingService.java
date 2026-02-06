package com.movie.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movie.booking.model.CitiesResponse;


public interface BookingService {

	List<CitiesResponse> getCitiesAndLanguages();

}
