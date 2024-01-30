package com.commerzbank.library.service;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.repository.api.Repository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookService {
    private final Repository<Book> bookRepository;

    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().contains(bookSearchCriteria.getAuthor()))
                .collect(Collectors.toList());
    }
}
