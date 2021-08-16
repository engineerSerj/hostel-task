package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.Role;
import org.hostel.domain.User;

@Data
@RequiredArgsConstructor
public class UserDto {

    private long id;
    private String name;
    private Role role;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
    }
}
