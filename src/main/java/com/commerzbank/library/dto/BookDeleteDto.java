package com.commerzbank.library.dto;

import com.commerzbank.library.dto.validation.annotation.Deleted;
import com.commerzbank.library.model.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BookDeleteDto {
    @Deleted
    private BookStatus bookStatus;
}
