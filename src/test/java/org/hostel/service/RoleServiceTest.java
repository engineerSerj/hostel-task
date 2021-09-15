package org.hostel.service;

import org.hostel.domain.Role;
import org.hostel.domain.RoleName;
import org.hostel.dto.RoleDto;
import org.hostel.repositoriy.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        roleService = new RoleService(roleRepository);
    }

    @Test
    void canCreateRole() {
        RoleDto role = new RoleDto();
        role.setId(1L);
        role.setRoleName(RoleName.ROLE_ADMIN);
        try {
            roleService.createRole(role);
        } catch (Exception e) {
            //throws expected NullPointerException because
            //userService method is trying to get result - Role role = roleRepository.save(new Role(roleDto))
            e.printStackTrace();
        }
        Role roleFromDTO = new Role(role);
        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);

        verify(roleRepository).save(roleArgumentCaptor.capture());
        Role capturedRole = roleArgumentCaptor.getValue();
        System.out.println(capturedRole.toString() + "\ncompared to \n" + roleFromDTO);
        assertThat(capturedRole).isEqualTo(roleFromDTO);
    }
}