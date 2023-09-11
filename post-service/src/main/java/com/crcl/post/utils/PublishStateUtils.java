package com.crcl.post.utils;

import com.crcl.common.domain.PublishState;
import com.crcl.post.domain.Post;

public class PublishStateUtils {

    public static void markInProgress(Post post) {
        post.setPublishState(PublishState.IN_PROGRESS);
    }

    public static void markPublished(Post post) {
        post.setPublishState(PublishState.PUBLISHED);
    }

    public static void markPublishFailed(Post post) {
        post.setPublishState(PublishState.PUBLISH_FAILED);
    }
}
