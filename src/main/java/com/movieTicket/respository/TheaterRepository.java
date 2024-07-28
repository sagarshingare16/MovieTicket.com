package com.movieTicket.respository;

import com.movieTicket.model.TheaterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterInfo,Long> {
    List<TheaterInfo> findByMovieId(Long movieId);
}
