package com.movie.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.booking.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long>{
	
	List<Theater> findAllByCityIdAndLanguageIdAndMovieId(long cityId, long languageId, long movieId);

}
