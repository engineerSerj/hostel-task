package org.hostel.service;

import org.hostel.dao.CategoryDaoImpl;
import org.hostel.entity.Category;
import org.hostel.entity.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryDaoImpl categoryDao;

    @Autowired
    public CategoryService(CategoryDaoImpl categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional
    public void add(Category category) {
        categoryDao.add(category);
    }

    public void remove(int id) {
        categoryDao.remove(id);
    }

    public List<CategoryName> getListCategory() {
        return categoryDao.getListCategory();
    }
}
