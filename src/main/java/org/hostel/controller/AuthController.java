package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.dto.RefreshTokenUserDto;
import org.hostel.dto.RegistredUserDto;
import org.hostel.dto.UserDto;
import org.hostel.exception.*;
import org.hostel.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> authenticateUser(@RequestBody RegistredUserDto userDto) {
        return userService.authenticateUser(userDto);
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistredUserDto> registerUser(@RequestBody RegistredUserDto userDto) throws RoleNotFoundException, UserAlreadyExists {
        // Create new user's account
        return userService.add(userDto);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenUserDto refreshTokenRequest) throws RefreshTokenNotFoundException, TokenRefreshException {
        return refreshTokenService.refreshtoken(refreshTokenRequest);
    }
}

