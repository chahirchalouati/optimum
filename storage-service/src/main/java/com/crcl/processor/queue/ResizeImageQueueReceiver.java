package com.crcl.processor.queue;


import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.processor.dto.ResizeImageRequest;
import org.springframework.messaging.Message;

public interface ResizeImageQueueReceiver {
    void onReceiveResizeImage(Message<AuthenticatedMessage<ResizeImageRequest>> message);
}
