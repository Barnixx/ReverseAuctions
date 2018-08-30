package pl.reverseAuctions.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {
    public void initialize(FutureDate constraint) {
    }

    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) return false;

        return date.isAfter(LocalDate.now());
    }
}
