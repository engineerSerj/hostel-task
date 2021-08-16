package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domains.Role;
import org.hostel.domains.User;

@Data
@RequiredArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private Role role;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
    }
}
