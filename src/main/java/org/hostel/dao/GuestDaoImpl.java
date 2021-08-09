package org.hostel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hostel.entity.Apartment;
import org.hostel.entity.Category;
import org.hostel.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GuestDaoImpl implements GuestDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Guest guest) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(guest);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(id);
    }

    @Override
    public List<Guest> getAll () {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Guest> cq = cb.createQuery(Guest.class);
        Root<Guest> rootEntry = cq.from(Guest.class);
        CriteriaQuery<Guest> all = cq.select(rootEntry);
        TypedQuery<Guest> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void setApartment(int id, Apartment apartment) {
        Session session = sessionFactory.getCurrentSession();
        Guest guest = getById(id);
        guest.setApartment(apartment);
        session.update(guest);
    }

    @Override
    public void editGuest(Guest guest) {
        Session session = sessionFactory.getCurrentSession();
        session.update(guest);
    }

    @Override
    public Guest getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Guest.class, id);
    }
}
