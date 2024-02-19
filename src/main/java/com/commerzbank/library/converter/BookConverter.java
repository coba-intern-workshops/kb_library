package com.commerzbank.library.converter;

import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.dto.BookStatusDto;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookConverter extends Converter<BookDto, Book> {

    public BookConverter() {
        super(BookConverter::convertToEntity, BookConverter::convertToDto);
    }

    private static BookDto convertToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .bookStatus(BookStatusDto.valueOf(book.getBookStatus().toString()))
                .build();
    }

    private static Book convertToEntity(BookDto bookDto) {
        return Book.builder()
                .id(UUID.randomUUID())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .bookStatus(BookStatus.valueOf(bookDto.getBookStatus().toString()))
                .build();
    }
}
