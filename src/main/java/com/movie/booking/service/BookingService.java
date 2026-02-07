package com.movie.booking.service;

import java.util.List;

import com.movie.booking.entity.Movies;
import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.TheaterResponse;


public interface BookingService {

	List<CitiesResponse> getCitiesAndLanguages();

	List<Movies> getTheatersList(String cityId, String languageId              )      ;

	TheaterResponse getShowTimeList(long cityId, long languageId, long movieId);

	TheaterResponse paymentProcess(PaymentRequest paymentRequest);

}
