package com.commerzbank.library.repository.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
    long count();
    void delete(T entity);
    void deleteAll(List<T> entities);
    void deleteAllByUUID(List<UUID> ids);
    void deleteByUUID(UUID uuid);
    boolean existsByUUID(UUID uuid);
    List<T> findAll();
    List<T> findAllByUUID(List<UUID> uuids);
    Optional<T> findByUUID(UUID uuid);
    T save(T entity);
    List<T> saveAll(List<T> entities);
}
