package com.commerzbank.library.book;

import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.BookStatus;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BookRepositoryImpl implements BookRepository {
    private static final List<Book> books = Arrays.asList(
            Book.builder()
                    .id(UUID.randomUUID())
                    .title("Java")
                    .bookStatus(BookStatus.AVAILABLE)
                    .author("Jan Kowalski")
                    .build(),
            Book.builder()
                    .id(UUID.randomUUID())
                    .title("Python")
                    .bookStatus(BookStatus.AVAILABLE)
                    .author("Robert Lewandowski")
                    .build(),
            Book.builder()
                    .id(UUID.randomUUID())
                    .title("C#")
                    .bookStatus(BookStatus.AVAILABLE)
                    .author("Arkadiusz Milik")
                    .build(),
            Book.builder()
                    .id(UUID.randomUUID())
                    .title("C++")
                    .bookStatus(BookStatus.AVAILABLE)
                    .author("Sławomir Peszko")
                    .build(),
            Book.builder()
                    .id(UUID.randomUUID())
                    .title("Kotlin")
                    .bookStatus(BookStatus.AVAILABLE)
                    .author("Wojciech Szczęsny")
                    .build()
    );

    @Override
    public List<Book> getAll() {
        return books;
    }
}
