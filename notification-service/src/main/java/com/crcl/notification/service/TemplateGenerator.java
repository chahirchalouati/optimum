package com.crcl.notification.service;

import java.util.Map;

public interface TemplateGenerator<T> {
    T generate(Map<String, Object> props, String id);
}
