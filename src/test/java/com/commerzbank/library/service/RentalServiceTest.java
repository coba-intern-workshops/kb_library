package com.commerzbank.library.service;

import com.commerzbank.library.converter.RentalConverter;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.model.*;
import com.commerzbank.library.repository.impl.RentalRepositoryImpl;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

class RentalServiceTest implements WithAssertions {
    private Rental rental1;
    private Rental rental2;
    private RentalRepositoryImpl rentalRepository;
    private RentalService rentalService;
    private static final int DEFAULT_FOUND_RENTALS_SIZE = 1;

    @BeforeEach
    void setUp() {
        rentalRepository = new RentalRepositoryImpl();
        rentalService = new RentalService(rentalRepository, new RentalConverter());
        Book book1 = Book.builder()
                .id(UUID.randomUUID())
                .title("testTitle1")
                .author("testAuthor1")
                .bookStatus(BookStatus.AVAILABLE)
                .build();
        Book book2 = Book.builder()
                .id(UUID.randomUUID())
                .title("testTitle2")
                .author("testAuthor2")
                .bookStatus(BookStatus.AVAILABLE)
                .build();
        Person person1 = Person.builder()
                .id(UUID.randomUUID())
                .firstName("Joe")
                .lastName("Doe")
                .userType(UserType.USER)
                .build();
        Person person2 = Person.builder()
                .id(UUID.randomUUID())
                .firstName("Jan")
                .lastName("Kowalski")
                .userType(UserType.USER)
                .build();
        rental1 = Rental.builder()
                .id(UUID.randomUUID())
                .book(book1)
                .person(person1)
                .rentedOn(LocalDate.now())
                .rentedUntil(LocalDate.now().plusWeeks(4))
                .build();
        rental2 = Rental.builder()
                .id(UUID.randomUUID())
                .book(book2)
                .person(person2)
                .rentedOn(LocalDate.now())
                .rentedUntil(LocalDate.now().plusWeeks(4))
                .build();
    }

    @Test
    void shouldReturnListOfRentals() {
        rentalRepository.save(rental1);
        rentalRepository.save(rental2);

        List<RentalDto> rentals = rentalService.findRentals(
                RentalSearchCriteria.builder()
                        .firstName("Joe")
                        .lastName("Doe")
                        .build()
        );

        assertThat(rentals).isNotNull().isNotEmpty();
        assertThat(rentals.size()).isEqualTo(DEFAULT_FOUND_RENTALS_SIZE);
    }
}