package org.hostel.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(long userId) {
        super(String.format("User is not found with id : %s", userId));
    }
}
