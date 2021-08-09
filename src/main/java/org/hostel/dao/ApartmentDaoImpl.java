package org.hostel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hostel.entity.Apartment;
import org.hostel.entity.Category;
import org.hostel.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ApartmentDaoImpl implements ApartmentDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(Apartment apartment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(apartment);
    }

    @Override
    public List<Guest> getGuestList(int id) {
        return getById(id).getGuestList();
    }

    @Override
    public Apartment getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Apartment.class, id);
    }

    @Override
    public int getRoomAmount(int id) {
        return getById(id).getRoomAmount();
    }

    @Override
    public void setCategory(int id, Category category) {
        Apartment apartment = getById(id);
        apartment.setCategory(category);
        Session session = sessionFactory.getCurrentSession();
        session.update(apartment);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(getById(id));
    }
}
