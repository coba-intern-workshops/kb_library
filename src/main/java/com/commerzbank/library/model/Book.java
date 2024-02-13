package com.commerzbank.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {
    private String title;
    private String author;
    @Setter
    private BookStatus bookStatus;
}
