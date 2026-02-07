package com.movie.booking.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.movie.booking.entity.Movies;
import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.LanguageResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.ShowTimes;
import com.movie.booking.model.TheaterResponse;
import com.movie.booking.service.BookingService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    // -------------------- GET /cities --------------------

    @Test
    void getCities_success() throws Exception {
        CitiesResponse city = new CitiesResponse();
        city.setCityId(1L);
        city.setCityName("Bangalore");
        city.setLanguages(List.of(new LanguageResponse(1L, "Kannada")));

        when(bookingService.getCitiesAndLanguages())
                .thenReturn(List.of(city));

        mockMvc.perform(get("/api/bookings/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value("Bangalore"))
                .andExpect(jsonPath("$[0].languages[0].languageName").value("Kannada"));
    }

    // -------------------- GET /theaters --------------------

    @Test
    void getTheatersList_success() throws Exception {
        Movies movie = new Movies();
        movie.setId(1L);
        movie.setMovieName("RajaKumara");

        when(bookingService.getTheatersList("1", "1"))
                .thenReturn(List.of(movie));

        mockMvc.perform(get("/api/bookings/theaters")
                        .param("city_id", "1")
                        .param("language_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movieName").value("RajaKumara"));
    }

    // -------------------- GET /showtimings --------------------

    @Test
    void getShowTimeList_success() throws Exception {
        ShowTimes showTime = new ShowTimes();
        showTime.setAvailableSeats(100);
        showTime.setPrice(200.0);
        showTime.setShowTime("13:30");

        TheaterResponse response = new TheaterResponse();
        response.setMovieId(1L);
        response.setMovieName("RajaKumara");
        response.setMovieTheaterCode("RAJAJI_560021");
        response.setShowTimes(List.of(showTime));

        when(bookingService.getShowTimeList(1L, 1L, 1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/bookings/showtimings")
                        .param("city_id", "1")
                        .param("language_id", "1")
                        .param("movie_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movieName").value("RajaKumara"))
                .andExpect(jsonPath("$.showTimes[0].availableSeats").value(100));
    }

    // -------------------- POST /payments --------------------

    @Test
    void paymentProcess_success() throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setUserId(1L);
        request.setMovieId(1L);
        request.setMovieName("RajaKumara");
        request.setMovieTheaterCode("RAJAJI_560021");
        request.setShowTime("13:30");
        request.setNoOfTickets(2);
        request.setPaymentType("CARD");

        when(bookingService.paymentProcess(any(PaymentRequest.class)))
                .thenReturn("Transaction is Successfull !!");

        mockMvc.perform(post("/api/bookings/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction is Successfull !!"));
    }
}
