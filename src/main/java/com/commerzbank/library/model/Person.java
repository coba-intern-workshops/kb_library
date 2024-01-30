package com.commerzbank.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Person extends AbstractEntity {
    private String firstName;
    private String lastName;
    private UserType userType;
}
