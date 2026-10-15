package com.vehiclerental.vehicle_rental_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehiclerental.vehicle_rental_system.entity.Rental;
import com.vehiclerental.vehicle_rental_system.service.RentalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // Create a new rental
    @PostMapping
    public Rental createRental(@Valid @RequestBody Rental rental) {
        return rentalService.createRental(rental);
    }

    // Get all rentals
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    // Return vehicle
    @PutMapping("/{id}/return")
    public Rental returnVehicle(@PathVariable Long id) {
        return rentalService.returnVehicle(id);
    }
    
 // Get rental history by customer
    @GetMapping("/customer/{customerId}")
    public List<Rental> getRentalsByCustomer(
            @PathVariable Long customerId) {

        return rentalService.getRentalsByCustomer(customerId);
    }
    
    
    
}