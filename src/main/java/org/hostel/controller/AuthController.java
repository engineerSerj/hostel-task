package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.dto.RefreshTokenUserDto;
import org.hostel.dto.RegistredUserDto;
import org.hostel.dto.UserDto;
import org.hostel.exception.RefreshTokenNotFoundException;
import org.hostel.exception.TokenRefreshException;
import org.hostel.jms.UserRegistrationTopicListener;
import org.hostel.service.RefreshTokenService;
import org.hostel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ObjectMessage;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final UserRegistrationTopicListener userRegistrationTopicListener;

    @Autowired
    @Qualifier("topicJmsTemplate")
    private JmsTemplate topicJmsTemplate;

    @PostMapping("/signin")
    public ResponseEntity<UserDto> authenticateUser(@RequestBody RegistredUserDto userDto) {
        return userService.authenticateUser(userDto);
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistredUserDto> registerUser(@RequestBody RegistredUserDto userDto) {
        // Create new user's account
        topicJmsTemplate.setPubSubDomain(true);
        topicJmsTemplate.send("topic", session -> {
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(userDto);
            return objectMessage;
        });
        return userRegistrationTopicListener.getResponseEntity();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenUserDto refreshTokenRequest) throws RefreshTokenNotFoundException, TokenRefreshException {
        return refreshTokenService.refreshtoken(refreshTokenRequest);
    }
}

