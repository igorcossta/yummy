package io.igorcossta.misc;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ListConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListMustNotBeEmpty {
    String message() default "List cannot be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
