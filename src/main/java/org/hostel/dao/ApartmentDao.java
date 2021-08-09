package org.hostel.dao;

import org.hostel.entity.Apartment;
import org.hostel.entity.Category;
import org.hostel.entity.Guest;
import java.util.List;

public interface ApartmentDao {

    void add(Apartment apartment);

    List<Guest> getGuestList(int id);

    Apartment getById(int id);

    int getRoomAmount(int id);

    void setCategory(int id, Category category);

    void remove(int id);
}
