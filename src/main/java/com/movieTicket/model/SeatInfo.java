package com.movieTicket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seat_info")
public class SeatInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;
    private boolean isOccupied;
    private int rowNumber;
    private int seatNumber;
    private Long theaterId;
    private Long movieId;
}
