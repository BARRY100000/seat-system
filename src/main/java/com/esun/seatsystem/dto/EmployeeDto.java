package com.esun.seatsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private String empId;
    private String name;
    private String email;
    private Long floorSeatSeq;
}