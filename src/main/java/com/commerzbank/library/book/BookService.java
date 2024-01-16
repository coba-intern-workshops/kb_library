package com.commerzbank.library.book;

import com.commerzbank.library.model.Book;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findBooks(SearchCriteria searchCriteria) {
        return bookRepository.getAll().stream()
                .filter(book -> book.getAuthor().contains(searchCriteria.getAuthor()))
                .collect(Collectors.toList());
    }
}
