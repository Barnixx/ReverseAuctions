package pl.reverseAuctions.validator;

import pl.reverseAuctions.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqeUserNameValidator implements ConstraintValidator<UniqeUserName, String> {

    final private UserService userService;

    public UniqeUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(UniqeUserName constraint) {
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userService.isUserExist(username);
    }
}
