package com.movieTicket.service;

import com.movieTicket.dtos.SeatBookingRequest;
import com.movieTicket.model.MovieInfo;
import com.movieTicket.model.SeatInfo;
import com.movieTicket.model.TheaterInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UserRequestService {
    List<MovieInfo> getAllMovies();
    List<TheaterInfo> getTheaterByMovieId(Long movieId);
    List<SeatInfo> getSeatsByTheaterId(Long theaterId);
    String bookSeats(SeatBookingRequest seatBookingRequest);
    MovieInfo addMovie(MovieInfo movieInfo, MultipartFile poster) throws IOException;
}
