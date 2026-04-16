package com.esun.seatsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatDto {
    private Long floorSeatSeq;
    private Integer floorNo;
    private Integer seatNo;
    private String empId;
    private String empName;
}