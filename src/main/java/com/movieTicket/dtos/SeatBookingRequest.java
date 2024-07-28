package com.movieTicket.dtos;

import com.movieTicket.model.SeatInfo;
import lombok.Data;

import java.util.List;

@Data
public class SeatBookingRequest {
    private Long theaterId;
    private List<SeatInfo> seatInfoList;
}
