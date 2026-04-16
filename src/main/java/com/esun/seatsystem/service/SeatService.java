package com.esun.seatsystem.service;

import com.esun.seatsystem.dto.EmployeeDto;
import com.esun.seatsystem.dto.SeatDto;
import com.esun.seatsystem.dto.SeatMapResponse;
import com.esun.seatsystem.entity.Employee;
import com.esun.seatsystem.entity.SeatingChart;
import com.esun.seatsystem.exception.BusinessException;
import com.esun.seatsystem.repository.EmployeeRepository;
import com.esun.seatsystem.repository.SeatJdbcRepository;
import com.esun.seatsystem.repository.SeatingChartRepository;
import com.esun.seatsystem.util.HtmlSanitizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    private final SeatJdbcRepository seatJdbcRepository;
    private final EmployeeRepository employeeRepository;
    private final SeatingChartRepository seatingChartRepository;
    private final HtmlSanitizer htmlSanitizer;

    public SeatService(SeatJdbcRepository seatJdbcRepository,
                       EmployeeRepository employeeRepository,
                       SeatingChartRepository seatingChartRepository,
                       HtmlSanitizer htmlSanitizer) {
        this.seatJdbcRepository = seatJdbcRepository;
        this.employeeRepository = employeeRepository;
        this.seatingChartRepository = seatingChartRepository;
        this.htmlSanitizer = htmlSanitizer;
    }

    public SeatMapResponse getSeatMap() {
        List<SeatDto> seats = seatJdbcRepository.getAllSeats();
        List<EmployeeDto> employees = seatJdbcRepository.getAllEmployees();
        return new SeatMapResponse(seats, employees);
    }

    @Transactional
    public void assignSeat(String empId, Long floorSeatSeq) {
        empId = htmlSanitizer.clean(empId);

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new BusinessException("找不到此員工"));

        SeatingChart seat = seatingChartRepository.findById(floorSeatSeq)
                .orElseThrow(() -> new BusinessException("找不到此座位"));

        if (employee.getEmpId() == null || employee.getEmpId().length() != 5) {
            throw new BusinessException("員工編號格式錯誤");
        }

        seatJdbcRepository.assignSeat(empId, seat.getFloorSeatSeq());
    }

    @Transactional
    public void clearSeat(Long floorSeatSeq) {
        SeatingChart seat = seatingChartRepository.findById(floorSeatSeq)
                .orElseThrow(() -> new BusinessException("找不到此座位"));

        seatJdbcRepository.clearSeat(seat.getFloorSeatSeq());
    }
}