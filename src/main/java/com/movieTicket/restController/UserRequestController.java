package com.movieTicket.restController;

import com.movieTicket.dtos.SeatBookingRequest;
import com.movieTicket.model.MovieInfo;
import com.movieTicket.model.SeatInfo;
import com.movieTicket.model.TheaterInfo;
import com.movieTicket.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/movie-service")
public class UserRequestController {

    @Autowired
    private UserRequestService userRequestService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieInfo>> getAllMovies(){
        return new ResponseEntity<>(userRequestService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/theaters")
    public ResponseEntity<List<TheaterInfo>> getTheaterByMovieId(Long movieId){
        return new ResponseEntity<>(userRequestService.getTheaterByMovieId(movieId),HttpStatus.OK);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<SeatInfo>> getSeatsByTheaterId(Long theaterId){
        return new ResponseEntity<>(userRequestService.getSeatsByTheaterId(theaterId),HttpStatus.OK);
    }

    @PostMapping("/book-ticket")
    public ResponseEntity<String> bookSeats(@RequestBody SeatBookingRequest request){
        return new ResponseEntity<>(userRequestService.bookSeats(request),HttpStatus.OK);
    }

    @GetMapping("/locations")
    public List<String> getLocations() {
        return Arrays.asList("Mumbai", "Delhi", "Bangalore", "Chennai");
    }

    @PostMapping(value = "/add-movie")
    public MovieInfo addMovie(@RequestPart MovieInfo movieInfo, @RequestPart MultipartFile poster){
        try {
            return userRequestService.addMovie(movieInfo,poster);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
