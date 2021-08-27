package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class RegistredUserDto {
    private long id;
    private String username;
    private String password;
    private List<String> roles;

    public RegistredUserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(role->role.getRoleName().name()).collect(Collectors.toList());
    }
}
