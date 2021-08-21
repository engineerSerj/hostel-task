package org.hostel.exception;

public class RoleAlreadyExists extends Exception {
    public RoleAlreadyExists(String role) {
        super(String.format("Role already exists : %s", role));
    }
}
