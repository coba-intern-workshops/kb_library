package com.commerzbank.library.dto.validation.annotation;

import com.commerzbank.library.dto.validation.validator.DeletedValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={DeletedValidator.class})
public @interface Deleted {
    String message() default "Book status must be DELETED";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
