package com.commerzbank.library.controller;

import com.commerzbank.library.dto.RentalCreateDto;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO
// kasowanie - zwalnianie stanu - aktualizacja statusu ksiazki i czy przypisana do usera
// przedluzanie - zmiana czasu oddania o 7 dni i czy przypisana do tego usera
// wypozyczenia

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<RentalDto>> findAllRentals() {
        return ResponseEntity.ok(rentalService.findAll());
    }

    @PostMapping
    public ResponseEntity<RentalDto> saveRental(@RequestBody RentalCreateDto rentalCreateDto) {
        return ResponseEntity.ok(rentalService.save(rentalCreateDto));
    }
}
