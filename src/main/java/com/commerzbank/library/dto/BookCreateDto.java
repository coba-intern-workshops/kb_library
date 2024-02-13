package com.commerzbank.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class BookCreateDto {
    private String title;
    private String author;
    private BookStatusDto bookStatus;
}
