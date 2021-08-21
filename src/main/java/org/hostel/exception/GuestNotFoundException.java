package org.hostel.exception;

public class GuestNotFoundException extends Exception {

    public GuestNotFoundException(long guestId) {
        super(String.format("Guest is not found with id : %s", guestId));
    }
}
