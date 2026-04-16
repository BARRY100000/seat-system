package com.esun.seatsystem.controller;

import com.esun.seatsystem.dto.AssignSeatRequest;
import com.esun.seatsystem.dto.ClearSeatRequest;
import com.esun.seatsystem.dto.SeatMapResponse;
import com.esun.seatsystem.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<SeatMapResponse> getSeatMap() {
        return ResponseEntity.ok(seatService.getSeatMap());
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignSeat(@Valid @RequestBody AssignSeatRequest request) {
        seatService.assignSeat(request.getEmpId(), request.getFloorSeatSeq());
        return ResponseEntity.ok(Map.of("message", "座位更新成功"));
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearSeat(@Valid @RequestBody ClearSeatRequest request) {
        seatService.clearSeat(request.getFloorSeatSeq());
        return ResponseEntity.ok(Map.of("message", "座位清除成功"));
    }
}