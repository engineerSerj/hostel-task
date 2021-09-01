package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.User;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class RegistredUserDto implements Serializable {
    private long id;
    private String username;
    private String password;
    private String roles;

    public RegistredUserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles().toString();
    }
}
