package com.paloit.security.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserRepository implements UserRepositoryInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private static final HashMap<String, User> users = new HashMap<>();

    @Override
    public User getUserByUserName(String username) {
        return users.get(username.toLowerCase());
    }

    @Override
    public User getUserByPassword(String password) {
        return users.values().stream().filter(user -> user.password.equals(password)).findAny().orElse(null);
    }

    @Override
    public void saveUser(User user) throws SaveUserException {
        List<Error> errors = new ArrayList<>();

        errors.addAll(validateUser(user));
        if (checkIfUserExists(user)) {
            errors.add(new Error("User " + user.username + " already exists"));
        }

        if(errors.size() > 0) {
            logUserValidationErrors(errors);
            throw new SaveUserException(user, errors);
        } else {
            addUserToMap(user);
        }

    }

    protected List<Error> validateUser(User user) {
        UserValidator userValidator = new UserValidator();
        return userValidator.validate(user);
    }

    private void logUserValidationErrors(List<Error> errors) {
        errors.forEach(error -> LOGGER.error(error.getMessage()));
    }

    protected boolean checkIfUserExists(User user) {
        return users.containsKey(user.username.toLowerCase());
    }

    protected void addUserToMap(User user) {
        users.put(user.username.toLowerCase(), user);
    }

    /**
     * This method is here to initialize the users into the application.
     * It's dirty but this is not part of the exercise.
     */
    @PostConstruct
    public void initData() {
        User user = new User();
        user.username = "admin";
        user.password = "password";
        user.firstName = "Admin";
        user.lastName = "Istrator";
        user.address = "Singapore";
        addUserToMap(user);
    }
}
