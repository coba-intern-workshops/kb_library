package com.commerzbank.library.repository.api;

import com.commerzbank.library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
}
