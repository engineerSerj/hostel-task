package org.hostel.service;

import org.hostel.dao.GuestDaoImpl;
import org.hostel.entity.Apartment;
import org.hostel.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GuestService {

    final private GuestDaoImpl guestDaoImpl;

    @Autowired
    public GuestService(GuestDaoImpl guestDaoImpl) {
        this.guestDaoImpl = guestDaoImpl;
    }

    @Transactional
    public void add(Guest guest) {
        guestDaoImpl.add(guest);
    }

    public void remove(int id) {
        guestDaoImpl.remove(id);
    }

    public List<Guest> getAll () {
        return guestDaoImpl.getAll();
    }

    @Transactional
    public void setApartment(int id, Apartment apartment) {
       guestDaoImpl.setApartment(id, apartment);
    }

    @Transactional
    public void editGuest(Guest guest) {
        guestDaoImpl.editGuest(guest);
    }
}
