package com.commerzbank.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class PersonDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private UserTypeDto userType;
}
