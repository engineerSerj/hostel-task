package org.hostel.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.RequiredArgsConstructor;
import org.hostel.actor.SpringExtension;
import org.hostel.dto.RoleDto;
import org.hostel.dto.UserDto;
import org.hostel.exception.UserNotFoundException;
import org.hostel.service.RoleService;
import org.hostel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final ActorSystem system;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> remove(@PathVariable("id") long id) throws UserNotFoundException {
        ActorRef removerActorRef = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("removerActor"), "removerActorRef");
        removerActorRef.tell (new UserDto(id), null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> setRole(@PathVariable("userId") int userId, @RequestBody RoleDto roleDto) throws UserNotFoundException {
        return userService.setRole(userId, roleDto);
    }

    @PostMapping("/newRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }
}
