package com.commerzbank.library.converter;

import com.commerzbank.library.dto.PersonCreateDto;
import com.commerzbank.library.dto.UserTypeDto;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.UserType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersonCreateConverter extends Converter<PersonCreateDto, Person> {

    public PersonCreateConverter() {
        super(PersonCreateConverter::convertToEntity, PersonCreateConverter::convertToDto);
    }

    private static PersonCreateDto convertToDto(Person person) {
        return PersonCreateDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .userType(UserTypeDto.valueOf(person.getUserType().toString()))
                .build();
    }

    private static Person convertToEntity(PersonCreateDto personCreateDto) {
        return Person.builder()
                .id(UUID.randomUUID())
                .firstName(personCreateDto.getFirstName())
                .lastName(personCreateDto.getLastName())
                .userType(UserType.valueOf(personCreateDto.getUserType().toString()))
                .build();
    }
}
