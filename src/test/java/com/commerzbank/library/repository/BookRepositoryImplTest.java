package com.commerzbank.library.repository;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.api.Repository;
import com.commerzbank.library.repository.impl.BookRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryImplTest {
    private Repository<Book> bookRepository;
    private Book book;
    private static final int DEFAULT_REPOSITORY_SIZE = 0;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();
        book = Book.builder()
                .id(UUID.randomUUID())
                .title("testTitle")
                .author("testAuthor")
                .bookStatus(BookStatus.AVAILABLE)
                .build();
    }

    @Test
    void shouldProperlyFindAllBooks() {
        assertNotNull(bookRepository);
        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.findAll().size());
    }

    @Test
    void shouldAddBookToList() {
        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.findAll().size());
        int REPOSITORY_SIZE_AFTER_ADDING_BOOK = DEFAULT_REPOSITORY_SIZE + 1;

        bookRepository.save(book);

        assertEquals(REPOSITORY_SIZE_AFTER_ADDING_BOOK, bookRepository.findAll().size());
    }

    @Test
    void shouldProperlySaveBookWithProperValues() {
        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook);
        assertEquals(book.getId(), savedBook.getId());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getBookStatus(), savedBook.getBookStatus());
    }

    @Test
    void shouldThrowIAEWhenNullPassed() {
        assertThrowsExactly(IllegalArgumentException.class, () -> bookRepository.save(null));
    }

}