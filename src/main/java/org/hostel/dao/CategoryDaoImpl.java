package org.hostel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hostel.entity.Category;
import org.hostel.entity.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CatrgoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(id);
    }

    @Override
    public List<CategoryName> getListCategory() {
        return new ArrayList<CategoryName>(Arrays.asList(CategoryName.values()));
    }
}
