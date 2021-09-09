package org.hostel.jms;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.actor.SpringExtension;
import org.hostel.dto.CategoryDto;
import org.hostel.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.apache.activemq.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;

@RequiredArgsConstructor
@Component
@Data
public class CategoryRemoveByIdQueueConsumer {

    private final CategoryService categoryService;
    private ResponseEntity<?> responseEntity;
    private final ActorSystem system;

    private static final Logger logger = LoggerFactory.getLogger(CategoryRemoveByIdQueueConsumer.class);

    @JmsListener(destination = "queue")
    public void consumeMessage(Message jsonMessage) throws JMSException {
        TextMessage textMessage = (TextMessage) jsonMessage;
        long id = Long.parseLong(textMessage.getText());
        logger.info("Queue receiver has got a textMessage ----> {}", textMessage.getText());
        ActorRef removerActorRef = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("removerActor"), "removerActorRef");
        removerActorRef.tell (new CategoryDto(id), null);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

}
