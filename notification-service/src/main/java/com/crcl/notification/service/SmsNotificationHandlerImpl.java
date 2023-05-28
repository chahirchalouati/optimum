package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.common.queue.QueuePublisher;
import com.crcl.notification.domain.NotificationType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class SmsNotificationHandlerImpl extends NotificationHandler {
    public SmsNotificationHandlerImpl(QueuePublisher notificationQueuePublisher) {
        super(notificationQueuePublisher);
    }

    @Override
    public NotificationResponse notifySync(NotificationRequest request, NotificationType type) {
        return null;
    }


    @Override
    public void notifyAsync(NotificationRequest request, NotificationType type) {

    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isSms();
    }

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 100)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(SmsNotificationHandlerImpl::processNumber)
                .sequential()
                .blockLast();
    }

    private static void processNumber(int number) {
        // Simulate some time-consuming operation
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processed number: " + number + " - Thread: " + Thread.currentThread().getName());
    }
}
