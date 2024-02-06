package com.commerzbank.library.converter;

import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.dto.UserTypeDto;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.model.UserType;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter extends Converter<PersonDto, Person> {
    public PersonConverter() {
        super(PersonConverter::convertToEntity, PersonConverter::convertToDto);
    }

    private static PersonDto convertToDto(Person person) {
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .userType(UserTypeDto.valueOf(person.getUserType().toString()))
                .build();
    }

    private static Person convertToEntity(PersonDto personDto) {
        return Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .userType(UserType.valueOf(personDto.getUserType().toString()))
                .build();
    }
}
