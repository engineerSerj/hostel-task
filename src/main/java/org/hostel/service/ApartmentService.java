package org.hostel.service;

import org.hostel.dao.ApartmentDao;
import org.hostel.entity.Apartment;
import org.hostel.entity.Category;
import org.hostel.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ApartmentService {

    private final ApartmentDao apartmentDao;

    @Autowired
    public ApartmentService(ApartmentDao apartmentDao) {
        this.apartmentDao = apartmentDao;
    }

    @Transactional
    public void add(Apartment apartment) {
        apartmentDao.add(apartment);
    }

    public List<Guest> getGuestList(Apartment apartment) {
        return apartment.getGuestList();
    }

    public int getRoomAmount(int id) {
        return apartmentDao.getRoomAmount(id);
    }

    @Transactional
    public void setCategory(int id, Category category) {
        apartmentDao.setCategory(id, category);
    }

    public void remove(int id) {
        apartmentDao.remove(id);
    }
}
