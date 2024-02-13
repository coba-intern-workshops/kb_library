package com.commerzbank.library.converter;

import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookStatusDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookCreateConverter extends Converter<BookCreateDto, Book> {
    public BookCreateConverter() {
        super(BookCreateConverter::convertToEntity, BookCreateConverter::convertToDto);
    }

    private static BookCreateDto convertToDto(Book book) {
        return BookCreateDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .bookStatus(BookStatusDto.valueOf(book.getBookStatus().toString()))
                .build();
    }

    private static Book convertToEntity(BookCreateDto bookCreateDto) {
        return Book.builder()
                .id(UUID.randomUUID())
                .title(bookCreateDto.getTitle())
                .author(bookCreateDto.getAuthor())
                .bookStatus(BookStatus.valueOf(bookCreateDto.getBookStatus().toString()))
                .build();
    }
}
