package com.commerzbank.library.controller;

import com.commerzbank.library.dto.PersonCreateDto;
import com.commerzbank.library.dto.PersonDto;
import com.commerzbank.library.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> findAllPeople() {
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonCreateDto personCreateDto) {
        return ResponseEntity.ok(personService.save(personCreateDto));
    }
}
