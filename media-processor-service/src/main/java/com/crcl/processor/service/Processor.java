package com.crcl.processor.service;

public interface Processor<T> {
    void process(T t);
}
