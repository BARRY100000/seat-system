package com.esun.seatsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClearSeatRequest {

    @NotNull(message = "座位序號不可為空")
    private Long floorSeatSeq;
}