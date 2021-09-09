package org.hostel.actor;

import akka.actor.UntypedActor;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.*;
import org.hostel.service.ApartmentService;
import org.hostel.service.CategoryService;
import org.hostel.service.GuestService;
import org.hostel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class RemoverActor extends UntypedActor {

    private static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);
    private final CategoryService categoryService;
    private final ApartmentService apartmentService;
    private final GuestService guestService;
    private final UserService userService;

    @Override
    public void onReceive(Object message) throws Throwable {
        long id;
        logger.info("DeletingActor has got the message");
        if (message instanceof CategoryDto) {
            id = (((CategoryDto) message).getId());
            getSender().tell(categoryService.remove(id), null);
        } else if (message instanceof ApartmentDto) {
            id = (((ApartmentDto) message).getId());
            getSender().tell(apartmentService.remove(id), null);
        } else if (message instanceof GuestDto) {
            id = (((GuestDto) message).getId());
            getSender().tell(guestService.remove(id), null);
        } else if (message instanceof UserDto) {
            id = (((UserDto) message).getId());
            getSender().tell(userService.remove(id), null);
        } else {
            unhandled(message);
        }
        getContext().stop(self());
        logger.info("DeletingActor has stopped");
    }
}
