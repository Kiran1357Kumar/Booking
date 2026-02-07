package com.movie.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.entity.Movies;
import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.TheaterResponse;
import com.movie.booking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/test")
    public String test() {
    	return "SUCCESS";
    }
    
    @GetMapping("/cities")
    public ResponseEntity<List<CitiesResponse>> getCities() {
    	List<CitiesResponse> response = bookingService.getCitiesAndLanguages();
    	 return ResponseEntity.ok(response);
    }
    
    @GetMapping("/theaters")
    public ResponseEntity<List<Movies>> getTheatersList(@RequestParam("city_id") String cityId, 
    		@RequestParam("language_id") String languageId ) {
    	List<Movies> movieList  = bookingService.getTheatersList(cityId, languageId);
    	 return ResponseEntity.ok(movieList);
    }
    
    @GetMapping("/showtimings")
    public ResponseEntity<TheaterResponse> getShowTimeList(@RequestParam("city_id") long cityId, 
    		@RequestParam("language_id") long languageId, @RequestParam("movie_id") long movieId ) {
    	TheaterResponse  showDetails  = bookingService.getShowTimeList(cityId, languageId, movieId);
    	 return ResponseEntity.ok(showDetails);
    }
    
    @PostMapping("/payments")
    public ResponseEntity<TheaterResponse> paymentProcess(@RequestBody PaymentRequest paymentRequest ) {
    	TheaterResponse  showDetails  = bookingService.paymentProcess(paymentRequest);
    	 return ResponseEntity.ok(showDetails);
    }
    
//    @PostMapping("/book")
//    public ResponseEntity<String> bookTickets(
//            @RequestBody BookingRequest request) {
//
//        String response = bookingService.bookTickets(request);
//        return ResponseEntity.ok(response);
//    }
    
}
