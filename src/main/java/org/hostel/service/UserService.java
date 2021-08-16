package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.UserNotFoundException;
import org.hostel.domains.Role;
import org.hostel.domains.User;
import org.hostel.dto.UserDto;
import org.hostel.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void add(UserDto userDto) {
        userRepository.save(new User(userDto));
    }

    public void remove(int id) {
        userRepository.deleteById(id);
    }

    public UserDto setRole(int id, Role role) throws UserNotFoundException {
        User user = getById(id);
        user.setRole(role);
        return new UserDto(user);
    }

    public User getById(int id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
