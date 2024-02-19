package com.commerzbank.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class RentalCreateDto {
    private BookDto book;
    private PersonDto person;
    private LocalDate rentedOn;
    private LocalDate rentedUntil;
}
