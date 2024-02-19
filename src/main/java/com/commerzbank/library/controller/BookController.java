package com.commerzbank.library.controller;

import com.commerzbank.library.dto.BookDeleteDto;
import com.commerzbank.library.dto.BookCreateDto;
import com.commerzbank.library.dto.BookDto;
import com.commerzbank.library.service.BookSearchCriteria;
import com.commerzbank.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<BookDto>> findAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping(path = "/all/filter")
    public ResponseEntity<List<BookDto>> findAllBooksByTitle(@RequestBody BookSearchCriteria bookSearchCriteria) {
        return ResponseEntity.ok(bookService.findBooks(bookSearchCriteria));
    }

    @PostMapping
    public ResponseEntity<BookDto> saveBook(@RequestBody BookCreateDto bookCreateDto) {
        return ResponseEntity.ok(bookService.save(bookCreateDto));
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable String id,
                                                    @RequestBody BookDeleteDto bookDeleteDto) {
        return ResponseEntity.ok(bookService.deleteBook(id, bookDeleteDto));
    }
}
