package com.commerzbank.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Person extends AbstractEntity {
    private String firstName;
    private String lastName;
    @Enumerated
    private UserType userType;
}
