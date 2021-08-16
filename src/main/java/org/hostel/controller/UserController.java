package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.UserNotFoundException;
import org.hostel.domain.Role;
import org.hostel.dto.UserDto;
import org.hostel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> add(@ModelAttribute("user") UserDto userDto) {
        return userService.add(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        return userService.remove(id);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> setRole(@PathVariable("userId") int userId, @RequestBody Role role) throws UserNotFoundException {
        return userService.setRole(userId, role);
    }
}
