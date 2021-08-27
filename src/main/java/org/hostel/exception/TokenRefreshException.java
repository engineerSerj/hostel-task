package org.hostel.exception;

public class TokenRefreshException extends Exception {
    public TokenRefreshException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
