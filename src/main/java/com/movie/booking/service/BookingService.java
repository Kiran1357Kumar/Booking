package com.movie.booking.service;

import java.util.List;

import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.MovieResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.TheaterResponse;


public interface BookingService {

	List<CitiesResponse> getCitiesAndLanguages();

	List<MovieResponse> getTheatersList(String cityId, String languageId              )      ;

	TheaterResponse getShowTimeList(long cityId, long languageId, long movieId);

	String paymentProcess(PaymentRequest paymentRequest);

}
