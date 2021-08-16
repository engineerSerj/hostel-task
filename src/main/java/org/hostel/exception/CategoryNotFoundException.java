package org.hostel.exception;

public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(long categoryId){
        super(String.format("Category is not found with id : '%s", categoryId));
    }
}
