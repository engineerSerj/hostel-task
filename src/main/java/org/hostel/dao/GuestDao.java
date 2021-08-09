package org.hostel.dao;

import org.hostel.entity.Apartment;
import org.hostel.entity.Category;
import org.hostel.entity.Guest;
import java.util.List;

public interface GuestDao {

    void add(Guest guest);

    void remove(int id);

    List<Guest> getAll();

    void setApartment (int id, Apartment apartment);

    void editGuest (Guest guest);

    Guest getById(int id);

}
