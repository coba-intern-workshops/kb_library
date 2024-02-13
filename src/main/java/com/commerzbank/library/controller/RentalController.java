package com.commerzbank.library.controller;

import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO tworzenie - walidacja czy nie wypozyczono ksiazki
// kasowanie - zwalnianie stanu - aktualizacja statusu ksiazki i czy przypisana do usera
// przedluzanie - zmiana czasu oddania o 7 dni i czy przypisana do tego usera
// wypozyczenia

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalDto>> findAllRentals() {
        return ResponseEntity.ok(rentalService.findAll());
    }
}
