package com.commerzbank.library.repository;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.api.RepositoryIfc;
import com.commerzbank.library.repository.impl.BookRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryImplTest {
    private RepositoryIfc<Book> bookRepository;
    private Book book1;
    private Book book2;
    private static final int DEFAULT_REPOSITORY_SIZE = 0;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();
        book1 = Book.builder()
                .id(UUID.randomUUID())
                .title("testTitle1")
                .author("testAuthor2")
                .bookStatus(BookStatus.AVAILABLE)
                .build();
        book2 = Book.builder()
                .id(UUID.randomUUID())
                .title("testTitle2")
                .author("testAuthor2")
                .bookStatus(BookStatus.AVAILABLE)
                .build();
    }

    @Test
    void shouldProperlyCountBooksWhenRepositoryIsEmpty() {
        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.count());
    }

    @Test
    void shouldProperlyCountBooksWhenRepositoryElements() {
        bookRepository.save(book1);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, bookRepository.count());
        bookRepository.save(book2);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, bookRepository.count());
    }

    @Test
    void shouldProperlyDeleteBook() {
        bookRepository.save(book1);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, bookRepository.count());

        bookRepository.delete(book1);

        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.count());
    }

    @Test
    void shouldProperlyDeleteAllBooks() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, bookRepository.count());

        List<Book> booksToDelete = Arrays.asList(book1, book2);
        bookRepository.deleteAll(booksToDelete);

        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.count());
    }

    @Test
    void shouldProperlyDeleteAllBooksByUUID() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, bookRepository.count());

        List<UUID> uuids = Arrays.asList(book1.getId(), book2.getId());
        bookRepository.deleteAllByUUID(uuids);

        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.count());
    }

    @Test
    void shouldProperlyDeleteBookByUUID() {
        bookRepository.save(book1);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, bookRepository.count());

        bookRepository.deleteByUUID(book1.getId());

        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.count());
    }

    @Test
    void shouldProperlyCheckIfBookExists() {
        bookRepository.save(book1);
        assertTrue(bookRepository.existsByUUID(book1.getId()));
    }

    @Test
    void shouldProperlyCheckIfBookDoesNotExist() {
        assertFalse(bookRepository.existsByUUID(book1.getId()));
    }

    @Test
    void shouldProperlyFindAllBooks() {
        assertNotNull(bookRepository);
        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.findAll().size());
    }

    @Test
    void shouldProperlyFindAllBooksByUUID() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<UUID> uuids = Arrays.asList(book1.getId(), book2.getId());

        List<Book> foundBooks = bookRepository.findAllByUUID(uuids);

        assertEquals(2, foundBooks.size());
    }

    @Test
    void shouldProperlyFindBookByUUID() {
        bookRepository.save(book1);
        Optional<Book> optionalBook = bookRepository.findByUUID(book1.getId());
        assertTrue(optionalBook.isPresent());
        Book foundBook = optionalBook.get();
        assertEquals(book1.getId(), foundBook.getId());
        assertEquals(book1.getAuthor(), foundBook.getAuthor());
        assertEquals(book1.getBookStatus(), foundBook.getBookStatus());
        assertEquals(book1.getTitle(), foundBook.getTitle());
    }

    @Test
    void shouldFailToFindBookByUUIDWhenBookDoesNotExist() {
        Optional<Book> optionalBook = bookRepository.findByUUID(book1.getId());
        assertTrue(optionalBook.isEmpty());
    }

    @Test
    void shouldProperlyAddBookToList() {
        assertEquals(DEFAULT_REPOSITORY_SIZE, bookRepository.findAll().size());

        bookRepository.save(book1);

        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, bookRepository.findAll().size());
    }

    @Test
    void shouldProperlySaveBookWithProperValues() {
        Book savedBook = bookRepository.save(book1);

        assertNotNull(savedBook);
        assertEquals(book1.getId(), savedBook.getId());
        assertEquals(book1.getTitle(), savedBook.getTitle());
        assertEquals(book1.getAuthor(), savedBook.getAuthor());
        assertEquals(book1.getBookStatus(), savedBook.getBookStatus());
    }

    @Test
    void shouldThrowIAEWhenNullPassed() {
        assertThrowsExactly(IllegalArgumentException.class, () -> bookRepository.save(null));
    }

    @Test
    void shouldProperlySaveAllBooks() {
        List<Book> booksToAdd = Arrays.asList(book1, book2);

        List<Book> savedBooks = bookRepository.saveAll(booksToAdd);

        assertEquals(2, savedBooks.size());
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, bookRepository.count());
    }

}