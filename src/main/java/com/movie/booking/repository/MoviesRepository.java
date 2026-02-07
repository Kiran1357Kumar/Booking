package com.movie.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.booking.entity.Movies;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long>{

	List<Movies> findAllByCityIdAndLanguageId(String cityId, String languageId);

}
