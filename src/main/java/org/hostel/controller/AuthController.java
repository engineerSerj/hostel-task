package org.hostel.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import lombok.RequiredArgsConstructor;
import org.hostel.actor.SpringExtension;
import org.hostel.dto.RefreshTokenUserDto;
import org.hostel.dto.RegisteredUserDto;
import org.hostel.exception.RefreshTokenNotFoundException;
import org.hostel.exception.TokenRefreshException;
import org.hostel.jms.UserRegistrationTopicListener;
import org.hostel.service.ApartmentService;
import org.hostel.service.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import javax.jms.ObjectMessage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final UserRegistrationTopicListener userRegistrationTopicListener;
    private final ActorSystem system;
    private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);

    @Autowired
    @Qualifier("topicJmsTemplate")
    private JmsTemplate topicJmsTemplate;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody RegisteredUserDto userDto) {
        ActorRef auth = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("authUserActor"), "auth");
        logger.info("authUserActor has been created ");
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> future = ask(auth, userDto, timeout);
        ResponseEntity<?> result = null;
        try {
            result = (ResponseEntity<?>) Await.result(future, duration);
        } catch (Exception e) {
            e.printStackTrace();
            system.stop(auth);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("authUserActor future {}", result.toString());
        return result;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegisteredUserDto> registerUser(@RequestBody RegisteredUserDto userDto) throws InterruptedException {
        // Create new user's account
        topicJmsTemplate.setPubSubDomain(true);
        topicJmsTemplate.send("topic", session -> {
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(userDto);
            return objectMessage;
        });
        Thread.sleep(350);
        return userRegistrationTopicListener.getResponseEntity();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenUserDto refreshTokenRequest) throws RefreshTokenNotFoundException, TokenRefreshException {
        return refreshTokenService.refreshtoken(refreshTokenRequest);
    }
}

