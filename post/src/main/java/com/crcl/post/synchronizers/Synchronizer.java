package com.crcl.post.synchronizers;

import com.crcl.core.dto.queue.events.DefaultQEvent;

public interface Synchronizer<T> {
    void synchronize(DefaultQEvent<T> event);
}
