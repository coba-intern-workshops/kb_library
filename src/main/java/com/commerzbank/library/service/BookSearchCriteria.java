package com.commerzbank.library.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookSearchCriteria {
    private String author;
}
