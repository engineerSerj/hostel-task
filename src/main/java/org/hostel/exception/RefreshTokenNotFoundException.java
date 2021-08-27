package org.hostel.exception;

public class RefreshTokenNotFoundException extends Exception {
    public RefreshTokenNotFoundException(String refreshToken) {
        super(String.format("RefreshToken is not found with name : %s", refreshToken));
    }
}
