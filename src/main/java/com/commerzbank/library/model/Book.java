package com.commerzbank.library.model;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Book {
    private UUID id;
    private String title;
    private String author;
    private BookStatus bookStatus;
}
