package com.crcl.post.synchronizers;

import com.crcl.core.dto.queue.events.QEvent;

public interface Synchronizer<T> {
    void synchronize(QEvent<T> event);
}
