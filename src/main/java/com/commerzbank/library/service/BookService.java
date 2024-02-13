package com.commerzbank.library.service;

import com.commerzbank.library.converter.BookConverter;
import com.commerzbank.library.converter.BookCreateConverter;
import com.commerzbank.library.dto.BookDeleteDto;
import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.exception.RecordNotFoundException;
import com.commerzbank.library.exception.RequestValidationException;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;
import com.commerzbank.library.repository.impl.BookRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepositoryImpl bookRepository;
    private final BookConverter bookConverter;
    private final BookCreateConverter bookCreateConverter;

    public List<BookDto> findBooks(BookSearchCriteria bookSearchCriteria) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().contains(bookSearchCriteria.getAuthor()))
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public void deleteAll(List<Book> books) {
        bookRepository.deleteAll(books);
    }

    public void deleteAllByUUID(List<UUID> ids) {
        bookRepository.deleteAllByUUID(ids);
    }

    public boolean existsByUUID(UUID uuid) {
        return bookRepository.existsByUUID(uuid);
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public BookDto findByUUID(UUID uuid) {
        return bookConverter.convertFromEntity(bookRepository.findByUUID(uuid)
                .orElseThrow(() -> new RecordNotFoundException("Book with id : " + uuid + " not found")));
    }

    public BookDto save(BookCreateDto book) {
        if (book == null) {
            throw new IllegalArgumentException("Object cannot be null");
        } else {
            Book bookEntity = bookCreateConverter.convertFromDto(book);
            return bookConverter.convertFromEntity(bookRepository.save(bookEntity));
        }
    }

    public List<BookDto> saveAll(List<Book> books) {
        return bookRepository.saveAll(books).stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public BookDto deleteBook(String id, BookDeleteDto bookDeleteDto) {
        //TODO dla chetnych: walidacja DTO dla requestu przed wywołaniem metody (bean validation)
        if (!bookDeleteDto.getBookStatus().equals(BookStatus.DELETED)) {
            throw new RequestValidationException("Status DELETED required");
        }

        Book book = bookConverter.convertFromDto(findByUUID(UUID.fromString(id)));
        if (book.getBookStatus().equals(BookStatus.DELETED)) {
            throw new RequestValidationException("Book status is already DELETED");
        }

        book.setBookStatus(bookDeleteDto.getBookStatus());
        return bookConverter.convertFromEntity(bookRepository.save(book));
    }
}
