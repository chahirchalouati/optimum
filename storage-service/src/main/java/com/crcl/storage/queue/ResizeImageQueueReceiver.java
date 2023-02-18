package com.crcl.storage.queue;


import com.crcl.storage.dto.ResizeImageRequest;
import org.springframework.messaging.Message;

public interface ResizeImageQueueReceiver {
    void onReceiveResizeImage(Message<ResizeImageRequest> message);
}
