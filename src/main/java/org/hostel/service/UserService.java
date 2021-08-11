package org.hostel.service;

import org.hostel.dao.UserDaoImpl;
import org.hostel.entity.Role;
import org.hostel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserService(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Transactional
    public void add(User user) {
        userDaoImpl.add(user);
    }

    public void remove(int id) {
        userDaoImpl.remove(id);
    }

    @Transactional
    public void setRole(int id, Role role) {
        userDaoImpl.setRole(id, role);
    }
}
