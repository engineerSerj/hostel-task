package org.hostel.jms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.service.CategoryService;
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

    @JmsListener(destination = "queue")
    public void consumeMessage(Message jsonMessage) throws CategoryNotFoundException, JMSException {
        TextMessage textMessage = (TextMessage) jsonMessage;
        long id = Long.parseLong(textMessage.getText());
        responseEntity = categoryService.remove(id);
    }

}
