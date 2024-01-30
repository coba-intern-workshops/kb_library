package com.commerzbank.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class PersonDto {
    private String firstName;
    private String lastName;
    private UserTypeDto userType;
}
