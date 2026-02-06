package com.movie.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.booking.entity.City;
import com.movie.booking.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long>{

}
