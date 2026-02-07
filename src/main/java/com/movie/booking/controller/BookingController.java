package com.movie.booking.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.MovieResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.TheaterResponse;
import com.movie.booking.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CitiesResponse>> getCities() {
    	List<CitiesResponse> response = bookingService.getCitiesAndLanguages();
    	 return ResponseEntity.ok(response);
    }
    
    @GetMapping("/theaters")
    public ResponseEntity<List<MovieResponse>> getTheatersList(@RequestParam("city_id") String cityId, 
    		@RequestParam("language_id") String languageId ) {
    	 List<MovieResponse> movieList  = bookingService.getTheatersList(cityId, languageId);
    	 return ResponseEntity.ok(movieList);
    }
    
    @GetMapping("/showtimings")
    public ResponseEntity<TheaterResponse> getShowTimeList(@RequestParam("city_id") long cityId, 
    		@RequestParam("language_id") long languageId, @RequestParam("movie_id") long movieId ) {
    	TheaterResponse  showDetails  = bookingService.getShowTimeList(cityId, languageId, movieId);
    	 return ResponseEntity.ok(showDetails);
    }
    
    @PostMapping("/payments")
    public ResponseEntity<String> paymentProcess(@Valid @RequestBody PaymentRequest paymentRequest ) {
    	log.info("Inside BookingController - Request start !! " +new Date()+ " time: "+new Date().getTime());

    	String  paymentMessage  = bookingService.paymentProcess(paymentRequest);
    	
    	log.info("Inside BookingController - Request start !! " +new Date()+ " time: "+new Date().getTime());

    	 return ResponseEntity.ok(paymentMessage);
    }
 
    
}
