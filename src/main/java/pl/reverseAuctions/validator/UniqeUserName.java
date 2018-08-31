package pl.reverseAuctions.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqeUserNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqeUserName {

    String message() default "{UniqueUserName.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
