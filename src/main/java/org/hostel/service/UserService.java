package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.RoleName;
import org.hostel.dto.RoleDto;

import org.hostel.exception.*;
import org.hostel.domain.Role;
import org.hostel.domain.User;
import org.hostel.dto.UserDto;
import org.hostel.repositoriy.RoleRepository;
import org.hostel.repositoriy.UserRepository;
import org.hostel.security.jwt.JwtUtils;
import org.hostel.security.util.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public ResponseEntity<UserDto> add(UserDto userDto) throws UserAlreadyExists, RoleNotFoundException {

        if (userRepository.existsByUsername(userDto.getUsername())) {
            // throw new UserAlreadyExists(userDto.getUsername());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        // Create new user's account
        User user = new User(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> userRoles = new HashSet<>();
        if (userDto.getRoles().contains("ROLE_ADMIN")) {
            userRoles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RoleNotFoundException(RoleName.ROLE_ADMIN.name())));
        } else {
            Role role = roleRepository.findByRoleName(RoleName.ROLE_RECEPTIONIST).orElseThrow(() -> new RoleNotFoundException(RoleName.ROLE_RECEPTIONIST.name()));
            userRoles.add(role);
        }
        user.setRoles(userRoles);
        return new ResponseEntity<>(new UserDto(userRepository.save(user)), HttpStatus.CREATED);
    }

    public ResponseEntity<UserDto> remove(long id) throws CategoryNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<UserDto> setRole(long id, RoleDto roleDto) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(new Role(roleDto));
        user.setRoles(userRoles);
        if (user.getRoles().contains(roleDto)) {
            return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UserDto userDtoWithToken = new UserDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);

        return new ResponseEntity<>(userDtoWithToken, HttpStatus.OK);
    }
}
