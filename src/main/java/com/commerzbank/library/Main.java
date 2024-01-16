package com.commerzbank.library;

import com.commerzbank.library.book.BookRepository;
import com.commerzbank.library.book.BookRepositoryImpl;
import com.commerzbank.library.book.BookService;
import com.commerzbank.library.book.SearchCriteria;

public class Main {
    public static void main(String[] args) {
        SearchCriteria searchCriteria = SearchCriteria.builder()
                        .author("Jan Kowalski")
                                .build();
        BookRepository bookRepository = new BookRepositoryImpl();
        BookService bookService = new BookService(bookRepository);
        System.out.println(bookService.findBooks(searchCriteria));
    }
}