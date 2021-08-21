package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.dto.RoleDto;
import org.hostel.dto.UserDto;
import org.hostel.exception.RoleAlreadyExists;
import org.hostel.exception.RoleNotFoundException;
import org.hostel.exception.UserAlreadyExists;
import org.hostel.service.RoleService;
import org.hostel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto) {
        return userService.authenticateUser(userDto);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) throws RoleNotFoundException, UserAlreadyExists {
        // Create new user's account
        return userService.add(userDto);
    }
}

