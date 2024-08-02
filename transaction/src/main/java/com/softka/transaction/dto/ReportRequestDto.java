package com.softka.transaction.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ReportRequestDto {

    private Long clientId;
    private LocalDate initialDate;
    private LocalDate finalDate;

}
