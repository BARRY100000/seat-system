package com.esun.seatsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AssignSeatRequest {

    @NotBlank(message = "員工編號不可為空")
    @Pattern(regexp = "^[0-9]{5}$", message = "員工編號必須為5碼數字")
    private String empId;

    @NotNull(message = "座位序號不可為空")
    private Long floorSeatSeq;
}