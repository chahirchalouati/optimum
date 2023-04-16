package com.crcl.post.synchronizers;

import com.crcl.common.dto.queue.QEvent;

public interface Synchronizer<T> {
    void synchronize(QEvent<T> event);
}
