package com.commerzbank.library.controller;

import com.commerzbank.library.dto.RentalDto;
import com.commerzbank.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDto>> findAllRentals() {
        return ResponseEntity.ok(rentalService.findAll());
    }
}
