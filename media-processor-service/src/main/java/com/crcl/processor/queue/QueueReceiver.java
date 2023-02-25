package com.crcl.processor.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.common.queue.ImageUploadEvent;
import org.springframework.messaging.Message;

public interface QueueReceiver {
    void onReceiveResizeImage(Message<AuthenticatedMessage<ImageUploadEvent>> message);
}
