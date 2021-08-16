package org.hostel.Exceptions;

public class ApartmentNotFoundException extends Exception{
    private int apartmentId;

    public ApartmentNotFoundException(int apartmentId){
        super(String.format("Apartment is not found with id : '%s", apartmentId));
    }
}
