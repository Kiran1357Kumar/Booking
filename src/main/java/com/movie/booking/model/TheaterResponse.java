package com.movie.booking.model;

import java.util.List;

public class TheaterResponse {
	
	 private long movieId;
	 private String movieName;
	 private String movieTheaterCode;
	 private List<ShowTimes> showTimes;
	 
	 public long getMovieId() {
		 return movieId;
	 }

	 public String getMovieName() {
		 return movieName;
	 }

	 public void setMovieName(String movieName) {
		 this.movieName = movieName;
	 }

	 public List<ShowTimes> getShowTimes() {
		 return showTimes;
	 }

	 public void setShowTimes(List<ShowTimes> showTimes) {
		 this.showTimes = showTimes;
	 }

	 public void setMovieId(long movieId) {
		 this.movieId = movieId;
	 }

	 public String getMovieTheaterCode() {
		 return movieTheaterCode;
	 }

	 public void setMovieTheaterCode(String movieTheaterCode) {
		 this.movieTheaterCode = movieTheaterCode;
	 }
	 
	 
	 

}
