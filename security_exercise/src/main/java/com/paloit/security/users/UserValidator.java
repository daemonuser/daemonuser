package com.paloit.security.users;


import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    private static final int MIN_PASSWORD_LENGTH = 6;

    protected List<Error> errors = new ArrayList<>();

    public List<Error> validate(User user) {
        validateFirstName(user);
        validateLastName(user);
        validateUserName(user);
        validatePassword(user);
        return errors;

    }

    protected void validateFirstName(User user) {
        if (StringUtils.isEmpty(user.firstName)) {
            errors.add(new Error("First name is empty"));
        }
    }

    protected void validateLastName(User user) {
        if (StringUtils.isEmpty(user.lastName)) {
            errors.add(new Error("Last name is empty"));
        }
    }

    protected void validateUserName(User user) {
        if (StringUtils.isEmpty(user.username)) {
            errors.add(new Error("Username is empty"));
        }
    }

    protected void validatePassword(User user) {
        if (!StringUtils.isEmpty(user.password)) {
            if (user.password.length() < MIN_PASSWORD_LENGTH) {
                errors.add(new Error("Password is not long enough"));
            }
        } else {
            errors.add(new Error("Password is empty"));
        }

    }

}
