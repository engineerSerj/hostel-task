package org.hostel.exception;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String username) {
        super(String.format("User already exists : %s", username));
    }
}
