package pl.reverseAuctions.validator;

import pl.reverseAuctions.user.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, User> {
    @Override
    public void initialize(ConfirmPassword confirmPassword) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        System.out.println(user.getPassword());
        System.out.println(user.getRepeatPassword());
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("password").addConstraintViolation();
            return false;
        }
        return true;
    }
}
