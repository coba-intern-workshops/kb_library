package com.commerzbank.library.service;

import com.commerzbank.library.converter.BookConverter;
import com.commerzbank.library.converter.BookCreateConverter;
import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookDeleteDto;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.dto.BookStatusDto;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.impl.BookRepositoryImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookServiceTest implements WithAssertions {
    private BookService bookService;

    @BeforeEach
    protected void setUp() {
        bookService = new BookService(new BookRepositoryImpl(), new BookConverter(), new BookCreateConverter());
    }

    @Test
    void shouldDeleteBook() {
        BookDto bookDto = bookService.save(BookCreateDto.builder()
                .title("TestBook")
                .author("TestAuthor")
                .bookStatus(BookStatusDto.AVAILABLE)
                .build());

        BookDto deletedBookDto = bookService.deleteBook(bookDto.getId().toString(),
                BookDeleteDto.builder().bookStatus(BookStatus.DELETED).build());

        assertThat(deletedBookDto).isNotNull();
        assertThat(deletedBookDto.getBookStatus()).isEqualTo(BookStatusDto.DELETED);
    }

    //TODO
    @Test
    void shouldThrowExceptionWhenBookDoesNotExist() {

    }

    @Test
    void shouldThrowExceptionWhenStatusIsDifferentThanDeleted() {

    }
}
