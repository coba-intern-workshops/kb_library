package com.commerzbank.library.repository.impl;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.repository.api.RepositoryIfc;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements RepositoryIfc<Book> {
    private final List<Book> books = new ArrayList<>();

    @Override
    public long count() {
        return books.size();
    }

    @Override
    public void delete(Book entity) {
        books.removeIf(book -> book.getId().equals(entity.getId()));
    }

    @Override
    public void deleteAll(List<Book> entities) {
        for (Book book : entities) {
            books.removeIf(b -> b.getId().equals(book.getId()));
        }
    }

    @Override
    public void deleteAllByUUID(List<UUID> ids) {
        books.removeIf(book -> ids.contains(book.getId()));
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        books.removeIf(book -> book.getId().equals(uuid));
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return books.stream()
                .anyMatch(book -> book.getId().equals(uuid));
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> findAllByUUID(List<UUID> uuids) {
        return books.stream()
                .filter(book -> uuids.contains(book.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findByUUID(UUID uuid) {
        return books.stream()
                .filter(book -> book.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        if (book == null) {
            throw new IllegalArgumentException();
        }
        Optional<Book> existingBook = books.stream()
                .filter(b -> b.getId().equals(book.getId()))
                .findFirst();
        existingBook.ifPresent(books::remove);
        books.add(book);
        return book;
    }

    @Override
    public List<Book> saveAll(List<Book> entities) {
        for (Book book : entities) {
            Optional<Book> existingBook = books.stream()
                    .filter(b -> b.getId().equals(book.getId()))
                    .findFirst();
            existingBook.ifPresent(books::remove);
        }
        books.addAll(entities);
        return entities;
    }
}
