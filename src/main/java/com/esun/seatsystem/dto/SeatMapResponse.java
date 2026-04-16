package com.esun.seatsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeatMapResponse {
    private List<SeatDto> seats;
    private List<EmployeeDto> employees;
}