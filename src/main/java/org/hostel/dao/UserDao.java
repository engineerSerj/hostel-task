package org.hostel.dao;

import org.hostel.entity.Role;
import org.hostel.entity.User;

public interface UserDao {

    void add(User user);

    void remove(int id);

    void setRole(int id, Role role);

    User getById(int id);
}
