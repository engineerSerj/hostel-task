package org.hostel.actor;

import lombok.RequiredArgsConstructor;
import org.hostel.dto.RegistredUserDto;
import org.hostel.exception.UserNotFoundException;
import org.hostel.service.ApartmentService;
import org.hostel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AkkaAuthService {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);

    public Object authenticateUser(long id, String username, String password, String roles) throws UserNotFoundException {
        logger.info("AkkaAuthService is sending message to userService");
        return userService.authenticateUser(new RegistredUserDto(id, username, password, roles));
    }
}
