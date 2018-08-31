package pl.reverseAuctions.validator;

import pl.reverseAuctions.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqeUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserService userService;

    public UniqeUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(UniqueUserEmail constraint) {
    }

    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return userService.isUserMailExist(mail);
    }
}
