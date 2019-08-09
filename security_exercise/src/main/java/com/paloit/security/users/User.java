package com.paloit.security.users;

public class User {

    public String username;
    public String password;
    public String address;
    public String firstName;
    public String lastName;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
