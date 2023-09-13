package io.igorcossta.misc;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListConstraintValidator implements ConstraintValidator<ListMustNotBeEmpty, List<String>> {

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return false;
        if (value.contains(null) || value.contains("")) return false;
        return true;
    }
}
