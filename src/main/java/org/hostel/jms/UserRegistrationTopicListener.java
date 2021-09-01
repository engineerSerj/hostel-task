package org.hostel.jms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.RegistredUserDto;
import org.hostel.exception.RoleNotFoundException;
import org.hostel.exception.UserAlreadyExists;
import org.hostel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@RequiredArgsConstructor
@Component
@Data
public class UserRegistrationTopicListener {

    private final UserService userService;
    private ResponseEntity<RegistredUserDto> responseEntity;

    @JmsListener(destination = "topic",
            containerFactory = "topicListenerFactory",
            subscription = "topic",
            id = "topicListener")
    public void receiveMessageFromTopic(Message jsonMessage) throws RoleNotFoundException, UserAlreadyExists, JMSException {
        ObjectMessage objectMessage = (ObjectMessage) jsonMessage;
        RegistredUserDto userDto =  (RegistredUserDto)objectMessage.getObject();
        responseEntity = userService.add(userDto);
    }
}
