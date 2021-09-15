package org.hostel.service;

import org.hostel.domain.RoleName;
import org.hostel.domain.User;
import org.hostel.dto.RegisteredUserDto;
import org.hostel.dto.RoleDto;
import org.hostel.dto.UserDto;
import org.hostel.exception.UserNotFoundException;
import org.hostel.repositoriy.RoleRepository;
import org.hostel.repositoriy.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private  PasswordEncoder encoder;
    @Mock
    private RoleRepository roleRepository;

    private UserService userService;
    private RegisteredUserDto registeredUserDto;
    private RoleDto role;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, encoder, null, roleRepository, null, null);
        registeredUserDto = new RegisteredUserDto();
        registeredUserDto.setId(1L);
        registeredUserDto.setUsername("test");
        registeredUserDto.setPassword("test");
        role = new RoleDto();
        role.setId(1L);
        role.setRoleName(RoleName.ROLE_ADMIN);
    }


    @Test
    void canAdd()  {
        try {
            userService.add(registeredUserDto);
        } catch (Exception e) {
            //throws expected NullPointerException because
            //userService method is trying to get result - new RegisteredUserDto(userRepository.save(user))
            e.printStackTrace();
        }
        User userFromDTO = new User(registeredUserDto);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        System.out.println(capturedUser.toString() + "\ncompared to \n" + userFromDTO);
         assertThat(capturedUser).isEqualTo(userFromDTO);
    }

    @Test
    void canRemove() throws UserNotFoundException {
        userService.remove(1L);
        verify(userRepository).findById(1L);
    }

    @Test
    void canSetRole() throws UserNotFoundException {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(registeredUserDto)));
        ResponseEntity<UserDto> resultResponseEntity = userService.setRole(registeredUserDto.getId(), role);
        System.out.println("registeredUserDto sent to setRole method " + registeredUserDto.toString());
        registeredUserDto.setRoles(role.getRoleName().name());
        ResponseEntity<UserDto> myResponseEntity = new ResponseEntity<>(new UserDto(registeredUserDto), HttpStatus.OK);
        System.out.println(resultResponseEntity.toString() + "\ncompared to \n" + myResponseEntity);
        assertThat(resultResponseEntity).isEqualTo(myResponseEntity);
    }

}