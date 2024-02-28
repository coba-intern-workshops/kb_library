package com.commerzbank.library.dto.validation.validator;

import com.commerzbank.library.dto.BookStatusDto;
import com.commerzbank.library.dto.validation.annotation.Deleted;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DeletedValidator implements ConstraintValidator<Deleted, BookStatusDto> {
    @Override
    public boolean isValid(BookStatusDto bookStatusDto, ConstraintValidatorContext constraintValidatorContext) {
        return bookStatusDto.equals(BookStatusDto.DELETED);
    }
}
