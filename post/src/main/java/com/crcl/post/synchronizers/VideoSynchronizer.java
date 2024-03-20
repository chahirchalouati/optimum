package com.crcl.post.synchronizers;

import com.crcl.core.dto.queue.ProcessableVideoEvent;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VideoSynchronizer implements Synchronizer<ProcessableVideoEvent> {


    @Override
    public void synchronize(DefaultQEvent<ProcessableVideoEvent> event) {

    }
}
