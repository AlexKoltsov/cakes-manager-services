package com.koltsov.cakes.manager.service;

public interface DataEnhancer<T, ID> {
    T enhance(ID id);
}
