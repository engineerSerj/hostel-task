package org.hostel.Exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(int userId) {
        super(String.format("User is not found with id : '%s", userId));
    }
}
