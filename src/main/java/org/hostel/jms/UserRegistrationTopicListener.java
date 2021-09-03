package org.hostel.jms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.RegistredUserDto;
import org.hostel.exception.RoleNotFoundException;
import org.hostel.exception.UserAlreadyExists;
import org.hostel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationTopicListener.class);

    @JmsListener(destination = "topic",
            containerFactory = "topicListenerFactory",
            subscription = "topic",
            id = "topicListener")
    public void receiveMessageFromTopic(Message jsonMessage) throws RoleNotFoundException, UserAlreadyExists, JMSException {
        ObjectMessage objectMessage = (ObjectMessage) jsonMessage;
        RegistredUserDto userDto =  (RegistredUserDto)objectMessage.getObject();
        logger.info("topic receiver has got a jsonMessage ---->{}", userDto.getClass());
        responseEntity = userService.add(userDto);
    }
}
