package com.commerzbank.library.model;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Person {
    private UUID id;
    private String firstName;
    private String lastName;
    private UserType userType;
}
