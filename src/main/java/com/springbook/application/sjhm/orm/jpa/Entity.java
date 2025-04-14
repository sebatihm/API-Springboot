package com.springbook.application.sjhm.orm.jpa;

public interface Entity<T extends EntityId> {
    T getId();
}