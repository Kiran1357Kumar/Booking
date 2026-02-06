package com.movie.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entity.City;
import com.movie.booking.entity.Language;
import com.movie.booking.model.CitiesResponse;
import com.movie.booking.model.LanguageResponse;
import com.movie.booking.repository.CityRepository;
import com.movie.booking.repository.LanguageRepository;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private CityRepository bookingRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
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

}
