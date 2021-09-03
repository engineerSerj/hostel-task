package org.hostel.jms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CategoryRemoveByIdQueueConsumer.class);

    @JmsListener(destination = "queue")
    public void consumeMessage(Message jsonMessage) throws CategoryNotFoundException, JMSException {
        TextMessage textMessage = (TextMessage) jsonMessage;
        long id = Long.parseLong(textMessage.getText());
        logger.info("Queue receiver has got a textMessage ----> {}", textMessage.getText());
        responseEntity = categoryService.remove(id);
    }

}
