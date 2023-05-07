package com.crcl.post.synchronizers;

import com.crcl.common.dto.queue.events.QEvent;

public interface Synchronizer<T> {
    void synchronize(QEvent<T> event);
}
