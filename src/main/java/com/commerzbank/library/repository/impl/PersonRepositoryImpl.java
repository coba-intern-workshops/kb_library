package com.commerzbank.library.repository.impl;

import com.commerzbank.library.model.Person;
import com.commerzbank.library.repository.api.RepositoryIfc;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl implements RepositoryIfc<Person> {
    private final List<Person> people = new ArrayList<>();

    @Override
    public long count() {
        return people.size();
    }

    @Override
    public void delete(Person entity) {
        people.removeIf(Person -> Person.getId().equals(entity.getId()));
    }

    @Override
    public void deleteAll(List<Person> entities) {
        for (Person Person : entities) {
            people.removeIf(b -> b.getId().equals(Person.getId()));
        }
    }

    @Override
    public void deleteAllByUUID(List<UUID> ids) {
        people.removeIf(Person -> ids.contains(Person.getId()));
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        people.removeIf(Person -> Person.getId().equals(uuid));
    }

    @Override
    public boolean existsByUUID(UUID uuid) {
        return people.stream()
                .anyMatch(Person -> Person.getId().equals(uuid));
    }

    @Override
    public List<Person> findAll() {
        return people;
    }

    @Override
    public List<Person> findAllByUUID(List<UUID> uuids) {
        return people.stream()
                .filter(Person -> uuids.contains(Person.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findByUUID(UUID uuid) {
        return people.stream()
                .filter(Person -> Person.getId().equals(uuid))
                .findFirst();
    }

    @Override
    public Person save(Person Person) {
        if (Person == null) {
            throw new IllegalArgumentException();
        }
        Optional<Person> existingPerson = people.stream()
                .filter(b -> b.getId().equals(Person.getId()))
                .findFirst();
        existingPerson.ifPresent(people::remove);
        people.add(Person);
        return Person;
    }

    @Override
    public List<Person> saveAll(List<Person> entities) {
        for (Person Person : entities) {
            Optional<Person> existingPerson = people.stream()
                    .filter(b -> b.getId().equals(Person.getId()))
                    .findFirst();
            existingPerson.ifPresent(people::remove);
        }
        people.addAll(entities);
        return entities;
    }
}
