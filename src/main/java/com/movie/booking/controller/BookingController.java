package com.movie.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.model.CitiesResponse;
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
    
//    @PostMapping("/book")
//    public ResponseEntity<String> bookTickets(
//            @RequestBody BookingRequest request) {
//
//        String response = bookingService.bookTickets(request);
//        return ResponseEntity.ok(response);
//    }
    
}
