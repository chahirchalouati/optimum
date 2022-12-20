package com.crcl.common.utils.generic;

public @FunctionalInterface interface Enhancer<T> {
    T enhance(T t);
}
