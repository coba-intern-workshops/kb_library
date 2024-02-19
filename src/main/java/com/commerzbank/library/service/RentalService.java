package com.commerzbank.library.service;

import com.commerzbank.library.converter.RentalConverter;
import com.commerzbank.library.converter.RentalCreateConverter;
import com.commerzbank.library.dto.RentalCreateDto;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.exception.BookIsRentedException;
import com.commerzbank.library.model.Book;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.Rental;
import com.commerzbank.library.repository.impl.RentalRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RentalService {
    private final RentalRepositoryImpl rentalRepository;
    private final RentalConverter rentalConverter;
    private final RentalCreateConverter rentalCreateConverter;

    public List<RentalDto> findRentals(RentalSearchCriteria rentalSearchCriteria) {
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getPerson().getFirstName().equals(rentalSearchCriteria.getFirstName()) &&
                        rental.getPerson().getLastName().equals(rentalSearchCriteria.getLastName()))
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Rental rental) {
        rentalRepository.delete(rental);
    }

    public void deleteAll(List<Rental> rentals) {
        rentalRepository.deleteAll(rentals);
    }

    public void deleteAllByUUID(List<UUID> ids) {
        rentalRepository.deleteAllByUUID(ids);
    }

    public void deleteByUUID(UUID uuid) {
        rentalRepository.deleteByUUID(uuid);
    }

    public boolean existsByUUID(UUID uuid) {
        return rentalRepository.existsByUUID(uuid);
    }

    public List<RentalDto> findAll() {
        return rentalRepository.findAll().stream()
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<RentalDto> findAllByUUID(List<UUID> uuids) {
        return rentalRepository.findAllByUUID(uuids).stream()
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public RentalDto findByUUID(UUID uuid) {
        return rentalConverter.convertFromEntity(rentalRepository.findByUUID(uuid).orElseThrow(NullPointerException::new));
    }

    public RentalDto save(RentalCreateDto rentalCreateDto) {
        if (rentalCreateDto == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        Rental rentalEntity = rentalCreateConverter.convertFromDto(rentalCreateDto);
        List<Rental> rentals = rentalRepository.findAll();
        boolean isBookRented = rentals.stream()
                .anyMatch(rental -> rental.getBook().getId().equals(rentalCreateDto.getBook().getId())
                        && rental.getReturned().equals(false));
        if (isBookRented) {
            throw new BookIsRentedException("Selected book is rented");
        }
        return rentalConverter.convertFromEntity(rentalRepository.save(rentalEntity));
    }

    public List<RentalDto> saveAll(List<Rental> rentals) {
        return rentalRepository.saveAll(rentals).stream()
                .map(rentalConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public RentalDto returnBook(UUID uuid) {
        Rental rental = rentalConverter.convertFromDto(findByUUID(uuid));
        if (rental.getReturned() || rental.getReturnedOn() != null) {
            throw new IllegalStateException("Book is already returned");
        }

        rental.setReturnedOn(LocalDate.now());
        rental.setReturned(true);
        return rentalConverter.convertFromEntity(rentalRepository.save(rental));
    }

    public RentalDto extendRentingDate(UUID uuid, int daysToExtend) {
        Rental rental = rentalConverter.convertFromDto(findByUUID(uuid));
        if (rental.getReturned() || rental.getReturnedOn() != null) {
            throw new IllegalStateException("Book is already returned");
        }

        rental.setRentedUntil(rental.getRentedUntil().plusDays(daysToExtend));
        return rentalConverter.convertFromEntity(rentalRepository.save(rental));
    }
}
