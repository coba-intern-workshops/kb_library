package com.commerzbank.library.repository.api;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T save(T object);
}
