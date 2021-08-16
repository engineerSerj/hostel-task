package org.hostel.Exceptions;

public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(int categoryId){
        super(String.format("Category is not found with id : '%s", categoryId));
    }
}
