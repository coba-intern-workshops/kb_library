package com.commerzbank.library.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Rental {
    private UUID id;
    private Book book;
    private Person person;
    private LocalDate rentedOn;
    private LocalDate rentedUntil;
    private LocalDate returnedOn;
    private Boolean returned;
}
