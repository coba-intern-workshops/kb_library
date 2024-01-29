package com.commerzbank.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Person {
    private String firstName;
    private String lastName;
    private UserType userType;
}
