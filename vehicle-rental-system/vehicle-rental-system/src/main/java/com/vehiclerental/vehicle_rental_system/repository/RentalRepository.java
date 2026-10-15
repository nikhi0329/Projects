package com.vehiclerental.vehicle_rental_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehiclerental.vehicle_rental_system.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByCustomerId(Long customerId);
}