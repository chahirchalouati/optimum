package com.crcl.post.synchronizers;

import com.crcl.common.dto.queue.VideoUpload;
import com.crcl.common.dto.queue.events.QEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VideoSynchronizerImpl implements Synchronizer<VideoUpload> {

    @Override
    public void synchronize(QEvent<VideoUpload> event) {
    }
}
