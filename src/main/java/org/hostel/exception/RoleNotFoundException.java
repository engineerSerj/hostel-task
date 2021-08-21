package org.hostel.exception;

public class RoleNotFoundException extends Exception {

    public RoleNotFoundException(String role) {
        super(String.format("Role is not found with username : %s. Create role!", role));
    }
}
