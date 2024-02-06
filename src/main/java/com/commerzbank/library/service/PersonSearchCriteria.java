package com.commerzbank.library.service;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonSearchCriteria {
    private String firstName;
    private String lastName;
}
