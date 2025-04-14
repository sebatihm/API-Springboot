package com.springbook.application.sjhm.orm.jpa;

public interface UniqueIdGenerator<T> {
    T getNextUniqueId();
}