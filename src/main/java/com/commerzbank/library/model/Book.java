package com.commerzbank.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Book extends AbstractEntity {
    private String title;
    private String author;
    @Setter
    @Enumerated
    private BookStatus bookStatus;
}
