package org.hostel.actor;

import akka.actor.UntypedActor;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.RegistredUserDto;
import org.hostel.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class AuthUserActor extends UntypedActor {

    private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);
    private final AkkaAuthService akkaAuthService;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof RegistredUserDto) {
            logger.info("AuthUserActor has got the message");
            long id = ((RegistredUserDto) message).getId();
            String username = ((RegistredUserDto) message).getUsername();
            String password = ((RegistredUserDto) message).getPassword();
            String roles = ((RegistredUserDto) message).getRoles();

            getSender().tell(akkaAuthService.authenticateUser(id, username, password, roles), getSelf());
            logger.info("AuthUserActor is sending message");
        } else {
            unhandled(message);
        }
        getContext().stop(self());
        logger.info("AuthUserActor has stopped");
    }
}
