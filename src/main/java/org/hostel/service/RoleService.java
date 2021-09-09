package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.Role;
import org.hostel.dto.RoleDto;
import org.hostel.repositoriy.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public ResponseEntity<RoleDto> createRole(RoleDto roleDto) {
        if (roleRepository.existsByRoleName(roleDto.getRoleName())) {
            logger.error("role already exists with role name {}", roleDto.getRoleName().name());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Role role = roleRepository.save(new Role(roleDto));
        logger.info("create role {} with id {}", role.getRoleName().name(), role.getId());
        return new ResponseEntity<>(new RoleDto(role), HttpStatus.CREATED);
    }
}
