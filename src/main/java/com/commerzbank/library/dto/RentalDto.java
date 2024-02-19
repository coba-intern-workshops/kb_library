package com.commerzbank.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class RentalDto {
    private UUID id;
    private BookDto book;
    private PersonDto person;
    private LocalDate rentedOn;
    private LocalDate rentedUntil;
    private LocalDate returnedOn;
    private Boolean returned;
}
