package com.commerzbank.library.service;

import com.commerzbank.library.converter.BookConverter;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.model.Book;
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

    public List<BookDto> findBooks(BookSearchCriteria bookSearchCriteria) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().contains(bookSearchCriteria.getAuthor()))
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Book book) {
        bookRepository.delete(book);
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
        return bookConverter.convertFromEntity(bookRepository.findByUUID(uuid).orElseThrow(NullPointerException::new));
    }

    public BookDto save(BookDto book) {
        if (book == null) {
            throw new IllegalArgumentException("Object cannot be null");
        } else {
            Book bookEntity = bookConverter.convertFromDto(book);
            return bookConverter.convertFromEntity(bookRepository.save(bookEntity));
        }
    }

    public List<BookDto> saveAll(List<Book> books) {
        return bookRepository.saveAll(books).stream()
                .map(bookConverter::convertFromEntity)
                .collect(Collectors.toList());
    }
}
