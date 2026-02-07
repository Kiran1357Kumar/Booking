package com.movie.booking.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entity.City;
import com.movie.booking.entity.Language;
import com.movie.booking.entity.Movies;
import com.movie.booking.entity.Payment;
import com.movie.booking.entity.Theater;
import com.movie.booking.entity.User;
import com.movie.booking.enums.PaymentTypes;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.LanguageResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.ShowTimes;
import com.movie.booking.model.TheaterResponse;
import com.movie.booking.repository.CityRepository;
import com.movie.booking.repository.LanguageRepository;
import com.movie.booking.repository.MoviesRepository;
import com.movie.booking.repository.PaymentRepository;
import com.movie.booking.repository.TheaterRepository;
import com.movie.booking.repository.UserRepository;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
	private static final String TRANSACTION_SUCCESS = "Transaction is Successfull !!";

	@Autowired
	private CityRepository bookingRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<CitiesResponse> getCitiesAndLanguages() {
		log.info("Inside BookingServiceImpl - getCitiesAndLanguages : ");

		List<City> cityList = bookingRepository.findAll();
		List<Language> languageList = languageRepository.findAll();
		List<CitiesResponse> responseList = createCityAndLanguageList(cityList, languageList);

		log.info("Inside BookingServiceImpl - getCitiesAndLanguages : " + responseList.size());

		return responseList;
	}

	private List<CitiesResponse> createCityAndLanguageList(List<City> cityList, List<Language> languageList) {
		List<CitiesResponse> resultList = new ArrayList<>();
		if (!cityList.isEmpty() || cityList.size() > 0) {
			for (City city : cityList) {
				CitiesResponse citiesResponse = new CitiesResponse();
				citiesResponse.setCityId(city.getId());
				citiesResponse.setCityName(city.getCityName());

				List<LanguageResponse> languages = languageList.stream()
						.filter(lang -> lang.getCityId().equals(city.getId())).map(lang -> {
							LanguageResponse response = new LanguageResponse(lang.getId(), lang.getLanguageName());
							return response;
						}).collect(Collectors.toList());
				citiesResponse.setLanguages(languages);
				resultList.add(citiesResponse);
			}
			return resultList;
		} else {
			throw new ResourceNotFoundException("No movies in this City !!");
		}
	}

	@Override
	public List<Movies> getTheatersList(String cityId, String languageId) throws ResourceNotFoundException {
		log.info("Inside BookingServiceImpl - getTheatersList : " + cityId + " and " + languageId);

		List<Movies> movieList = moviesRepository.findAllByCityIdAndLanguageId(cityId, languageId);
		if (movieList.isEmpty() || movieList.size() == 0) {
			throw new ResourceNotFoundException("No movies in this City !!");
		}
		log.info("Inside BookingServiceImpl - getTheatersList : " + movieList.size());

		return movieList;
	}

	@Override
	public TheaterResponse getShowTimeList(long cityId, long languageId, long movieId) {
		log.info("Inside BookingServiceImpl - getShowTimeList : city " + cityId + "language :" + languageId
				+ "movieId : " + movieId);

		TheaterResponse response = new TheaterResponse();
		List<ShowTimes> showList = new ArrayList<>();
		List<Theater> theaterList = theaterRepository.findAllByCityIdAndLanguageIdAndMovieId(cityId, languageId,
				movieId);
		if (theaterList.isEmpty() || theaterList.size() > 0) {
			for (Theater theater : theaterList) {
				ShowTimes showTimes = new ShowTimes();
				showTimes.setAvailableSeats(theater.getAvailableSeats());
				showTimes.setPrice(theater.getMoviePrice());
				showTimes.setShowTime(String.valueOf(theater.getShowTime()));
				showList.add(showTimes);
			}
			response.setMovieId(theaterList.get(0).getMovieId());
			response.setMovieName(theaterList.get(0).getMovieName());
			response.setMovieTheaterCode(theaterList.get(0).getMovieTheaterCode());
			response.setShowTimes(showList);
		} else {
			throw new ResourceNotFoundException("No movies in this Theater !!");
		}
		log.info("Inside BookingServiceImpl - getShowTimeList ::  sucessfully return show list !!");

		return response;
	}

	@Override
	public String paymentProcess(PaymentRequest paymentRequest) {
		log.info("Inside BookingServiceImpl - paymentProcess " + paymentRequest.toString());

		try {
			Optional<User> userOptional = userRepository.findById(paymentRequest.getUserId());
			Optional<Theater> theaterOptional = theaterRepository.findByMovieIdAndMovieTheaterCodeAndShowTime(
					paymentRequest.getMovieId(), paymentRequest.getMovieTheaterCode(),
					LocalTime.parse(paymentRequest.getShowTime()));
			double moviePrice = theaterOptional.get().getMoviePrice();
			double finalAmount = 0.0;
			log.info("Inside BookingServiceImpl - paymentProcess " + userOptional.get() + " :: "
					+ theaterOptional.get());
			if (userOptional.isPresent()) {
				User user = userOptional.get();

				if (paymentRequest.getNoOfTickets() > 3) {
					if (theaterOptional.get().getAvailableSeats() > paymentRequest.getNoOfTickets()) {
						double totalAmount = moviePrice * (double) paymentRequest.getNoOfTickets();
						double percentage = (totalAmount / 30.0) * 100;
						finalAmount = totalAmount - percentage;
						if (user.getAccountBalance() < finalAmount) {
							throw new ResourceNotFoundException("Insufficient found in account !!");
						}
					}else {
						throw new ResourceNotFoundException("The Required number of tickets is not available !!");
					}
				}
				LocalTime currentTime = LocalTime.now();
				LocalTime targetTime = LocalTime.of(13, 0);
				if (currentTime.isAfter(targetTime)) {
					double totalAmount = moviePrice * (double) paymentRequest.getNoOfTickets();
					double percentage = (totalAmount * 20.0) / 100;
					finalAmount = totalAmount - percentage;
					if (user.getAccountBalance() < finalAmount) {
						throw new ResourceNotFoundException("Insufficient found in account !!");
					}
				} else {
					finalAmount = moviePrice * (double) paymentRequest.getNoOfTickets();
				}
				synchronized (BookingServiceImpl.class) {
					processPaymentTransation(user, paymentRequest, finalAmount);
					log.info("Inside BookingServiceImpl - paymentProcess :: Transaction is success !! ");
				}
			} else {
				throw new ResourceNotFoundException("User Not Found !!");
			}

		} catch (Exception e) {
			throw new ResourceNotFoundException("Exception occurs during payment with : "
					+ paymentRequest.getMovieName() + " and " + paymentRequest.getShowTime());
		}
		return TRANSACTION_SUCCESS;
	}

	private void processPaymentTransation(User user, PaymentRequest paymentRequest, double finalAmount) {
		Payment payment = new Payment();
		payment.setUserId(user.getId());
		payment.setUserName(user.getUserName());
		payment.setMobileNumber(user.getUserName());
		payment.setMovieName(paymentRequest.getMovieName());
		payment.setPaymentType(PaymentTypes.checkPaymentType(paymentRequest.getPaymentType()));
		payment.setAmountCredited(finalAmount);
		payment.setTicketsBooked((long) paymentRequest.getNoOfTickets());
		paymentRepository.save(payment);

		double balanceAmount = user.getAccountBalance() - finalAmount;
		user.setAccountBalance(balanceAmount);
		userRepository.save(user);

	}

}
