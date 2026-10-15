package com.vehiclerental.vehicle_rental_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehiclerental.vehicle_rental_system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}