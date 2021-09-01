package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.Role;
import org.hostel.domain.RoleName;

@Data
@RequiredArgsConstructor
public class RoleDto {
    private long id;
    private RoleName roleName;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
    }
}
