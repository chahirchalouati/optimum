package com.crcl.post.queue;

import com.crcl.post.queue.request.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ProductQueueReceiver {

    @RabbitListener(queues = "#{queueConfiguration.UPDATE_PAYMENT_QUEUE}")
    public void updatePaymentQueue(Message<MessageRequest<String>> message) {
        var payload = message.getPayload();
        log.info("received message" + payload.getPayload());
    }


}
