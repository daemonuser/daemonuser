package com.paloit.security.users;

import java.util.List;

public class SaveUserException extends Exception {

    private List<Error> errors;

    public SaveUserException(User user, List<Error> errors) {
        super("Problem Saving User: "+user.toString());
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
