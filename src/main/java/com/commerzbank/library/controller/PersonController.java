package com.commerzbank.library.controller;

import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/people")
    public ResponseEntity<List<PersonDto>> findAllPeople() {
        return ResponseEntity.ok(personService.findAll());
    }
}
