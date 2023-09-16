package com.crcl.post.synchronizers;

import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.queue.events.QEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessableVideoSynchronizer implements Synchronizer<ProcessableVideo> {


    @Override
    public void synchronize(DefaultQEvent<ProcessableVideo> event) {

    }
}
