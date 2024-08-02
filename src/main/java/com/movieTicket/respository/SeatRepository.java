package com.movieTicket.respository;

import com.movieTicket.model.SeatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<SeatInfo,Long> {
    List<SeatInfo> findByTheaterId(Long theaterId);
    SeatInfo findByTheaterIdAndMovieIdAndRowNumberAndSeatNumber(Long theaterId, Long movieId, String rowNumber, int seatNumber);
}
