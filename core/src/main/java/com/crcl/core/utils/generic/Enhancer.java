package com.crcl.core.utils.generic;

public @FunctionalInterface interface Enhancer<T> {
    T enhance(T t);
}
