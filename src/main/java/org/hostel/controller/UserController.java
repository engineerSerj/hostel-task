package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.dto.RoleDto;
import org.hostel.dto.UserDto;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.exception.RoleAlreadyExists;
import org.hostel.exception.UserNotFoundException;
import org.hostel.service.RoleService;
import org.hostel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> remove(@PathVariable("id") long id) throws CategoryNotFoundException {
        return userService.remove(id);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> setRole(@PathVariable("userId") int userId, @RequestBody RoleDto roleDto) throws UserNotFoundException {
        return userService.setRole(userId, roleDto);
    }

    @PostMapping("/newRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) throws RoleAlreadyExists {
        return roleService.createRole(roleDto);
    }
}
