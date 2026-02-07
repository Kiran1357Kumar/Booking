package com.movie.booking.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.booking.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long>{

    @Query("SELECT t FROM Theater t WHERE t.cityId = :cityId AND t.languageId = :languageId AND t.movieId = :movieId")
    List<Theater> findAllByCityIdAndLanguageIdAndMovieId(long cityId, long languageId, long movieId);

    @Query("SELECT t FROM Theater t WHERE t.movieId = :movieId")
    Theater findByMovieId(long movieId);

	Optional<Theater> findByMovieIdAndMovieTheaterCodeAndShowTime(long movieId, String movieTheaterCode, LocalTime localTime);
	
}
