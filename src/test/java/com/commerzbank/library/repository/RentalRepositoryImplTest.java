package com.commerzbank.library.repository;

import com.commerzbank.library.model.*;
import com.commerzbank.library.repository.api.Repository;
import com.commerzbank.library.repository.impl.RentalRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RentalRepositoryImplTest {
    private Repository<Rental> rentalRepository;
    private Rental rental1;
    private Rental rental2;
    private static final int DEFAULT_REPOSITORY_SIZE = 0;

    @BeforeEach
    void setUp() {
        rentalRepository = new RentalRepositoryImpl();
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
    void shouldProperlyCountRentalsWhenRepositoryIsEmpty() {
        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.count());
    }

    @Test
    void shouldProperlyCountRentalsWhenRepositoryElements() {
        rentalRepository.save(rental1);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, rentalRepository.count());
        rentalRepository.save(rental2);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, rentalRepository.count());
    }

    @Test
    void shouldProperlyDeleteRental() {
        rentalRepository.save(rental1);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, rentalRepository.count());

        rentalRepository.delete(rental1);

        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.count());
    }

    @Test
    void shouldProperlyDeleteAllRentals() {
        rentalRepository.save(rental1);
        rentalRepository.save(rental2);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, rentalRepository.count());

        List<Rental> RentalsToDelete = Arrays.asList(rental1, rental2);
        rentalRepository.deleteAll(RentalsToDelete);

        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.count());
    }

    @Test
    void shouldProperlyDeleteAllRentalsByUUID() {
        rentalRepository.save(rental1);
        rentalRepository.save(rental2);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, rentalRepository.count());

        List<UUID> uuids = Arrays.asList(rental1.getId(), rental2.getId());
        rentalRepository.deleteAllByUUID(uuids);

        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.count());
    }

    @Test
    void shouldProperlyDeleteRentalByUUID() {
        rentalRepository.save(rental1);
        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, rentalRepository.count());

        rentalRepository.deleteByUUID(rental1.getId());

        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.count());
    }

    @Test
    void shouldProperlyCheckIfRentalExists() {
        rentalRepository.save(rental1);
        assertTrue(rentalRepository.existsByUUID(rental1.getId()));
    }

    @Test
    void shouldProperlyCheckIfRentalDoesNotExist() {
        assertFalse(rentalRepository.existsByUUID(rental1.getId()));
    }

    @Test
    void shouldProperlyFindAllRentals() {
        assertNotNull(rentalRepository);
        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.findAll().size());
    }

    @Test
    void shouldProperlyFindAllRentalsByUUID() {
        rentalRepository.save(rental1);
        rentalRepository.save(rental2);
        List<UUID> uuids = Arrays.asList(rental1.getId(), rental2.getId());

        List<Rental> foundRentals = rentalRepository.findAllByUUID(uuids);

        assertEquals(2, foundRentals.size());
    }

    @Test
    void shouldProperlyFindRentalByUUID() {
        rentalRepository.save(rental1);
        Optional<Rental> optionalRental = rentalRepository.findByUUID(rental1.getId());
        assertTrue(optionalRental.isPresent());
        Rental foundRental = optionalRental.get();
        assertEquals(rental1.getId(), foundRental.getId());
        assertEquals(rental1.getId(), foundRental.getId());
        assertEquals(rental1.getBook(), foundRental.getBook());
        assertEquals(rental1.getPerson(), foundRental.getPerson());
        assertEquals(rental1.getRentedOn(), foundRental.getRentedOn());
        assertEquals(rental1.getRentedUntil(), foundRental.getRentedUntil());
    }

    @Test
    void shouldFailToFindRentalByUUIDWhenRentalDoesNotExist() {
        Optional<Rental> optionalRental = rentalRepository.findByUUID(rental1.getId());
        assertTrue(optionalRental.isEmpty());
    }

    @Test
    void shouldProperlyAddRentalToList() {
        assertEquals(DEFAULT_REPOSITORY_SIZE, rentalRepository.findAll().size());

        rentalRepository.save(rental1);

        assertEquals(DEFAULT_REPOSITORY_SIZE + 1, rentalRepository.findAll().size());
    }

    @Test
    void shouldProperlySaveRentalWithProperValues() {
        Rental savedRental = rentalRepository.save(rental1);

        assertNotNull(savedRental);
        assertEquals(rental1.getId(), savedRental.getId());
        assertEquals(rental1.getBook(), savedRental.getBook());
        assertEquals(rental1.getPerson(), savedRental.getPerson());
        assertEquals(rental1.getRentedOn(), savedRental.getRentedOn());
        assertEquals(rental1.getRentedUntil(), savedRental.getRentedUntil());
    }

    @Test
    void shouldThrowIAEWhenNullPassed() {
        assertThrowsExactly(IllegalArgumentException.class, () -> rentalRepository.save(null));
    }

    @Test
    void shouldProperlySaveAllRentals() {
        List<Rental> RentalsToAdd = Arrays.asList(rental1, rental2);

        List<Rental> savedRentals = rentalRepository.saveAll(RentalsToAdd);

        assertEquals(2, savedRentals.size());
        assertEquals(DEFAULT_REPOSITORY_SIZE + 2, rentalRepository.count());
    }
}
