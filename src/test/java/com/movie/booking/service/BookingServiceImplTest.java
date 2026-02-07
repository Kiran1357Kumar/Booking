package com.movie.booking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.movie.booking.entity.City;
import com.movie.booking.entity.Language;
import com.movie.booking.entity.Movies;
import com.movie.booking.entity.Payment;
import com.movie.booking.entity.Theater;
import com.movie.booking.entity.User;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.repository.CityRepository;
import com.movie.booking.repository.LanguageRepository;
import com.movie.booking.repository.MoviesRepository;
import com.movie.booking.repository.PaymentRepository;
import com.movie.booking.repository.TheaterRepository;
import com.movie.booking.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private MoviesRepository moviesRepository;

    @Mock
    private TheaterRepository theaterRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentRepository paymentRepository;

    // -------------------- getCitiesAndLanguages --------------------

    @Test
    void getCitiesAndLanguages_success() {
        City city = new City();
        city.setId(1L);
        city.setCityName("Bangalore");

        Language language = new Language();
        language.setId(1L);
        language.setCityId(1L);
        language.setLanguageName("Kannada");

        when(cityRepository.findAll()).thenReturn(List.of(city));
        when(languageRepository.findAll()).thenReturn(List.of(language));

        var response = bookingService.getCitiesAndLanguages();

        assertEquals(1, response.size());
        assertEquals("Bangalore", response.get(0).getCityName());
        assertEquals(1, response.get(0).getLanguages().size());
    }

    // -------------------- getTheatersList --------------------

    @Test
    void getTheatersList_success() {
        Movies movie = new Movies();
        movie.setId(1L);

        when(moviesRepository.findAllByCityIdAndLanguageId("1", "1"))
                .thenReturn(List.of(movie));

        List<Movies> result = bookingService.getTheatersList("1", "1");

        assertFalse(result.isEmpty());
    }

    @Test
    void getTheatersList_notFound() {
        when(moviesRepository.findAllByCityIdAndLanguageId("1", "1"))
                .thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class,
                () -> bookingService.getTheatersList("1", "1"));
    }

    // -------------------- getShowTimeList --------------------

    @Test
    void getShowTimeList_success() {
        Theater theater = new Theater();
        theater.setMovieId(1L);
        theater.setMovieName("RajaKumara");
        theater.setAvailableSeats((long)100);
        theater.setMoviePrice(200.0);
        theater.setShowTime(LocalTime.of(13, 30));
        theater.setMovieTheaterCode("RAJAJI_560021");

        when(theaterRepository.findAllByCityIdAndLanguageIdAndMovieId(1L, 1L, 1L))
                .thenReturn(List.of(theater));

        var response = bookingService.getShowTimeList(1L, 1L, 1L);

        assertEquals("RajaKumara", response.getMovieName());
        assertEquals(1, response.getShowTimes().size());
    }

    // -------------------- paymentProcess --------------------

    @Test
    void paymentProcess_success() {
        User user = new User();
        user.setId(1L);
        user.setUserName("John");
        user.setAccountBalance(5000.0);

        Theater theater = new Theater();
        theater.setMoviePrice(200.0);
        theater.setAvailableSeats((long)10);

        PaymentRequest request = new PaymentRequest();
        request.setUserId(1L);
        request.setMovieId(1L);
        request.setMovieName("RajaKumara");
        request.setMovieTheaterCode("RAJAJI_560021");
        request.setShowTime("13:30");
        request.setNoOfTickets(2);
        request.setPaymentType("UPI");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(theaterRepository.findByMovieIdAndMovieTheaterCodeAndShowTime(
                1L, "RAJAJI_560021", LocalTime.parse("13:30")))
                .thenReturn(Optional.of(theater));

        String response = bookingService.paymentProcess(request);

        assertEquals("Transaction is Successfull !!", response);
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void paymentProcess_userNotFound() {
        PaymentRequest request = new PaymentRequest();
        request.setUserId(99L);

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> bookingService.paymentProcess(request));
    }
}
