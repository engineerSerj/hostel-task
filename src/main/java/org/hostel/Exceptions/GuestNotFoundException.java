package org.hostel.Exceptions;

public class GuestNotFoundException extends Exception {

    public GuestNotFoundException(int guestId) {
        super(String.format("Guest is not found with id : '%s", guestId));
    }
}
