package com.paloit.security.users;

interface UserRepositoryInterface {

    User getUserByUserName(String username);

    User getUserByPassword(String password);

    void saveUser(User user) throws SaveUserException;
}
