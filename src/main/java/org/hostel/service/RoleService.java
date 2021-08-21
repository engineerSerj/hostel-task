package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.Guest;
import org.hostel.domain.Role;
import org.hostel.dto.RoleDto;
import org.hostel.exception.RoleAlreadyExists;
import org.hostel.repositoriy.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public ResponseEntity<RoleDto> createRole (RoleDto roleDto) throws RoleAlreadyExists {
        roleRepository.save(new Role(roleDto));
        return new ResponseEntity<>(roleDto, HttpStatus.CREATED);
    }
}
