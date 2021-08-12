package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domains.Role;
import org.hostel.domains.User;
import org.hostel.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public void remove(int id) {
        userRepository.deleteById(id);
    }

    public void setRole(int id, Role role) {
        Optional<User> apartment = userRepository.findById(id);
        apartment.ifPresent(value -> value.setRole(role));

    }

    public User getById(int id) {
        return  userRepository.getById(id);
    }
}
