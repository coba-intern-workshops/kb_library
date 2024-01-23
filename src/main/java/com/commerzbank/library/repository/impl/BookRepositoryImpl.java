package com.commerzbank.library.repository.impl;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.repository.api.Repository;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements Repository<Book> {
    private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book save(Book book) {
        if (book == null) {
            throw new IllegalArgumentException();
        } else {
            Book bookToSave = Book.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .bookStatus(book.getBookStatus())
                            .build();
            books.add(bookToSave);
            return bookToSave;
        }
    }
}
