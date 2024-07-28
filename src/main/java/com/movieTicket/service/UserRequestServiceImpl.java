package com.movieTicket.service;

import com.movieTicket.dtos.SeatBookingRequest;
import com.movieTicket.model.MovieInfo;
import com.movieTicket.model.SeatInfo;
import com.movieTicket.model.TheaterInfo;
import com.movieTicket.respository.MovieRepository;
import com.movieTicket.respository.SeatRepository;
import com.movieTicket.respository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserRequestServiceImpl implements UserRequestService{

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<MovieInfo> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<TheaterInfo> getTheaterByMovieId(Long movieId) {
        return theaterRepository.findByMovieId(movieId);
    }

    @Override
    public List<SeatInfo> getSeatsByTheaterId(Long theaterId) {
        return seatRepository.findByTheaterId(theaterId);
    }

    @Override
    public String bookSeats(SeatBookingRequest seatBookingRequest) {
        List<SeatInfo> seatInfoList = seatBookingRequest.getSeatInfoList();
        for(SeatInfo seatInfo : seatInfoList){
            SeatInfo existingSeat = seatRepository.findByTheaterIdAndMovieIdAndRowNumberAndSeatNumber(seatBookingRequest.getTheaterId(),
                    seatInfo.getTheaterId(), seatInfo.getRowNumber(),seatInfo.getSeatNumber());
            if(existingSeat != null && !existingSeat.isOccupied()) {
                existingSeat.setOccupied(true);
                seatRepository.save(existingSeat);
            }
        }
        return "Seats booked successfully.";
    }

    @Override
    public MovieInfo addMovie(MovieInfo movieInfo, MultipartFile poster) throws IOException {
        movieInfo.setPosterName(poster.getOriginalFilename());
        movieInfo.setPosterType(poster.getContentType());
        movieInfo.setPoster(poster.getBytes());
        return movieRepository.save(movieInfo);
    }

}
