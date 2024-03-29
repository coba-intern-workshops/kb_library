package com.commerzbank.library.dto;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private BookStatusDto bookStatus;
}
