package com.esun.seatsystem.repository;

import com.esun.seatsystem.dto.EmployeeDto;
import com.esun.seatsystem.dto.SeatDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public SeatJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SeatDto> getAllSeats() {
        String sql = "CALL sp_get_all_seats()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new SeatDto(
                rs.getLong("floor_seat_seq"),
                rs.getInt("floor_no"),
                rs.getInt("seat_no"),
                rs.getString("emp_id"),
                rs.getString("name")
        ));
    }

    public List<EmployeeDto> getAllEmployees() {
        String sql = "CALL sp_get_all_employees()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new EmployeeDto(
                rs.getString("emp_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getObject("floor_seat_seq") == null ? null : rs.getLong("floor_seat_seq")
        ));
    }

    public void assignSeat(String empId, Long floorSeatSeq) {
        String sql = "CALL sp_assign_seat(?, ?)";
        jdbcTemplate.update(sql, empId, floorSeatSeq);
    }

    public void clearSeat(Long floorSeatSeq) {
        String sql = "CALL sp_clear_seat(?)";
        jdbcTemplate.update(sql, floorSeatSeq);
    }
}