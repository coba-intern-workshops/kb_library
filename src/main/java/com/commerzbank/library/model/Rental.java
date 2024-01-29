package com.commerzbank.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Rental {
    private Book book;
    private Person person;
    private LocalDate rentedOn;
    private LocalDate rentedUntil;
    private LocalDate returnedOn;
    private Boolean returned;
}
