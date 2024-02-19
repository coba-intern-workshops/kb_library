package com.commerzbank.library.converter;

import com.commerzbank.library.dto.RentalCreateDto;
import com.commerzbank.library.model.Rental;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RentalCreateConverter extends Converter<RentalCreateDto, Rental> {

    public RentalCreateConverter() {
        super(RentalCreateConverter::convertToEntity, RentalCreateConverter::convertToDto);
    }

    private static RentalCreateDto convertToDto(Rental rental) {
        return RentalCreateDto.builder()
                .book(new BookConverter().convertFromEntity(rental.getBook()))
                .person(new PersonConverter().convertFromEntity(rental.getPerson()))
                .rentedOn(rental.getRentedOn())
                .rentedUntil(rental.getRentedUntil())
                .build();
    }

    private static Rental convertToEntity(RentalCreateDto rentalCreateDto) {
        return Rental.builder()
                .id(UUID.randomUUID())
                .book(new BookConverter().convertFromDto(rentalCreateDto.getBook()))
                .person(new PersonConverter().convertFromDto(rentalCreateDto.getPerson()))
                .rentedOn(rentalCreateDto.getRentedOn())
                .rentedUntil(rentalCreateDto.getRentedUntil())
                .returned(false)
                .returnedOn(null)
                .build();
    }
}
