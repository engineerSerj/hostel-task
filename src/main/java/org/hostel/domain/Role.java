package org.hostel.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.RoleDto;
import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(RoleDto role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
    }
}
