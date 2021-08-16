package org.hostel.exception;

public class ApartmentNotFoundException extends Exception{
    private int apartmentId;

    public ApartmentNotFoundException(long apartmentId){
        super(String.format("Apartment is not found with id : '%s", apartmentId));
    }
}
