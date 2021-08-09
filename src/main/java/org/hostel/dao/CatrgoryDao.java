package org.hostel.dao;


import org.hostel.entity.Category;
import org.hostel.entity.CategoryName;

import java.util.List;

public interface CatrgoryDao {

    void add(Category category);

    void remove(int id);

    List<CategoryName> getListCategory ();
}
