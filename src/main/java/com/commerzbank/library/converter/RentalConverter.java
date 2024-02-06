package com.commerzbank.library.converter;

import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter extends Converter<RentalDto, Rental> {
    public RentalConverter() {
        super(RentalConverter::convertToEntity, RentalConverter::convertToDto);
    }

    private static RentalDto convertToDto(Rental rental) {
        return RentalDto.builder()
                .book(new BookConverter().convertFromEntity(rental.getBook()))
                .person(new PersonConverter().convertFromEntity(rental.getPerson()))
                .rentedOn(rental.getRentedOn())
                .rentedUntil(rental.getRentedUntil())
                .returnedOn(rental.getReturnedOn())
                .returned(rental.getReturned())
                .build();
    }

    private static Rental convertToEntity(RentalDto rentalDto) {
        return Rental.builder()
                .book(new BookConverter().convertFromDto(rentalDto.getBook()))
                .person(new PersonConverter().convertFromDto(rentalDto.getPerson()))
                .rentedOn(rentalDto.getRentedOn())
                .rentedUntil(rentalDto.getRentedUntil())
                .returnedOn(rentalDto.getReturnedOn())
                .returned(rentalDto.getReturned())
                .build();
    }
}
