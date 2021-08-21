package org.hostel.repositoriy;

import org.hostel.domain.Role;
import org.hostel.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName (RoleName name);
}
