package org.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.User;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegisteredUserDto implements Serializable {
    @Nullable
    private long id;
    private String username;
    private String password;
    private String roles;

    public RegisteredUserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles().toString();
    }
}
