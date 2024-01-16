package com.commerzbank.library.book;

import com.commerzbank.library.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();
}
