package com.movie.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entity.City;
import com.movie.booking.entity.Language;
import com.movie.booking.entity.Movies;
import com.movie.booking.entity.Theater;
import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.LanguageResponse;
import com.movie.booking.model.PaymentRequest;
import com.movie.booking.model.ShowTimes;
import com.movie.booking.model.TheaterResponse;
import com.movie.booking.repository.CityRepository;
import com.movie.booking.repository.LanguageRepository;
import com.movie.booking.repository.MoviesRepository;
import com.movie.booking.repository.TheaterRepository;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private CityRepository bookingRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	@Override
	public List<CitiesResponse> getCitiesAndLanguages() {
		List<City> cityList = bookingRepository.findAll();
		List<Language> languageList = languageRepository.findAll();
		List<CitiesResponse> responseList  = createCityAndLanguageList(cityList, languageList);
		return responseList;
	}

	private List<CitiesResponse> createCityAndLanguageList(List<City> cityList, List<Language> languageList) {
		List<CitiesResponse> resultList = new ArrayList<>();
		if(!cityList.isEmpty() || cityList.size() > 0) {
			for(City city : cityList) {
				CitiesResponse citiesResponse = new CitiesResponse();
				citiesResponse.setCityId(city.getId());
				citiesResponse.setCityName(city.getCityName());
				
				List<LanguageResponse> languages = languageList.stream().filter( lang -> lang.getCityId().equals(city.getId()) )
												.map(lang -> {
													LanguageResponse response = new LanguageResponse(lang.getId(), lang.getLanguageName());
													return response;
												}).collect(Collectors.toList());
				citiesResponse.setLanguages(languages);
				resultList.add(citiesResponse);
			}
			return resultList;
		}else {
			return null; //Need to add Exception
		}
		
		
	}

	@Override
	public List<Movies> getTheatersList(String cityId, String languageId) {
		List<Movies> movieList = moviesRepository.findAllByCityIdAndLanguageId(cityId, languageId);
		return movieList;
	}

	@Override
	public TheaterResponse getShowTimeList(long cityId, long languageId, long movieId) {
		TheaterResponse response = new TheaterResponse();
		List<ShowTimes> showList = new ArrayList<>();
		List<Theater> theaterList = theaterRepository.findAllByCityIdAndLanguageIdAndMovieId(cityId, languageId, movieId);
		if(theaterList.isEmpty() || theaterList.size() > 0 ) {
			for(Theater theater : theaterList) {
				ShowTimes showTimes = new ShowTimes();
				showTimes.setAvailableSeats(theater.getAvailableSeats());
				showTimes.setPrice(theater.getMoviePrice());
				showTimes.setShowTime(String.valueOf(theater.getLocalDateTime()));
				showList.add(showTimes);
			}
			response.setMovieId(theaterList.get(0).getMovieId());
			response.setMovieName(theaterList.get(0).getMovieName());
			response.setShowTimes(showList);
		}else {
			//exception
		}
		
		
		return response;
	}

	@Override
	public TheaterResponse paymentProcess(PaymentRequest paymentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
