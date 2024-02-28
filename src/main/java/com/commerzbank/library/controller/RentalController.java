package com.commerzbank.library.controller;

import com.commerzbank.library.dto.RentalCreateDto;
import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//TODO
// kasowanie - czy przypisana do usera
// przedluzanie - czy przypisana do tego usera
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

    @PutMapping(path = "/id/{id}/extend")
    public ResponseEntity<RentalDto> extendRent(@PathVariable UUID id) {
        return ResponseEntity.ok(rentalService.extendRent(id));
    }

    @PostMapping(path = "/id/{id}/return")
    public ResponseEntity<RentalDto> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(rentalService.returnBook(id));
    }
}
