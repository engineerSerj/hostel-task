package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domains.Role;
import org.hostel.domains.User;
import org.hostel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User add(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void remove(int id) {
        userRepository.deleteById(id);
    }
    @Transactional
    public void setRole(int id, Role role) {
        Optional<User> apartment = userRepository.findById(id);
        apartment.ifPresent(value -> value.setRole(role));

    }

    public User getById(int id) {
        return  userRepository.getById(id);
    }
}
