package com.movieTicket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "theater_info")
public class TheaterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theaterId;
    private String theaterName;
    private String theaterLocation;
    private Long movieId;
}
