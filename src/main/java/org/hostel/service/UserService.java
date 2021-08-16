package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.UserNotFoundException;
import org.hostel.domain.Role;
import org.hostel.domain.User;
import org.hostel.dto.UserDto;
import org.hostel.repositoriy.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<?> add(UserDto userDto) {
        userRepository.save(new User(userDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> remove(long id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<UserDto> setRole(long id, Role role) throws UserNotFoundException {
        User user = getById(id);
        user.setRole(role);
        if (user.getRole().equals(role)) {
            return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public User getById(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
