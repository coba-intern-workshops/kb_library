package com.commerzbank.library.service;

import com.commerzbank.library.converter.PersonConverter;
import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.model.Person;
import com.commerzbank.library.repository.impl.PersonRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepositoryImpl personRepository;
    private final PersonConverter personConverter;

    public List<PersonDto> findPeople(PersonSearchCriteria personSearchCriteria) {
        return personRepository.findAll().stream()
                .filter(person -> person.getFirstName().contains(personSearchCriteria.getFirstName()) &&
                        person.getLastName().contains(personSearchCriteria.getLastName()))
                .map(personConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public void deleteAll(List<Person> people) {
        personRepository.deleteAll(people);
    }

    public void deleteAllByUUID(List<UUID> ids) {
        personRepository.deleteAllByUUID(ids);
    }

    public boolean existsByUUID(UUID uuid) {
        return personRepository.existsByUUID(uuid);
    }

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(personConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public PersonDto findByUUID(UUID uuid) {
        return personConverter.convertFromEntity(personRepository.findByUUID(uuid).orElseThrow(NullPointerException::new));
    }

    public PersonDto save(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Object cannot be null");
        } else {
            return personConverter.convertFromEntity(personRepository.save(person));
        }
    }

    public List<PersonDto> saveAll(List<Person> people) {
        return personRepository.saveAll(people).stream()
                .map(personConverter::convertFromEntity)
                .collect(Collectors.toList());
    }
}
