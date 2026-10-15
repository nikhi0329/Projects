package com.vehiclerental.vehicle_rental_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vehiclerental.vehicle_rental_system.entity.Customer;
import com.vehiclerental.vehicle_rental_system.entity.Rental;
import com.vehiclerental.vehicle_rental_system.entity.Vehicle;
import com.vehiclerental.vehicle_rental_system.exception.CustomerNotFoundException;
import com.vehiclerental.vehicle_rental_system.exception.RentalNotFoundException;
import com.vehiclerental.vehicle_rental_system.exception.VehicleNotFoundException;
import com.vehiclerental.vehicle_rental_system.repository.CustomerRepository;
import com.vehiclerental.vehicle_rental_system.repository.RentalRepository;
import com.vehiclerental.vehicle_rental_system.repository.VehicleRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public RentalService(
            RentalRepository rentalRepository,
            VehicleRepository vehicleRepository,
            CustomerRepository customerRepository) {

        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    // Create a new rental
    public Rental createRental(Rental rental) {

        // 1. Check whether customer exists
        Customer customer = customerRepository.findById(rental.getCustomerId())
                .orElseThrow(() ->
                    new CustomerNotFoundException(
                        "Customer with ID " + rental.getCustomerId() + " not found"));

        // 2. Find vehicle
        Vehicle vehicle = vehicleRepository.findById(rental.getVehicleId())
                .orElseThrow(() ->
                    new VehicleNotFoundException(
                        "Vehicle with ID " + rental.getVehicleId() + " not found"));

        // 3. Check vehicle availability
        if (!vehicle.getStatus().equalsIgnoreCase("AVAILABLE")) {
            throw new IllegalStateException(
                    "Vehicle with ID " + vehicle.getId() + " is not available");
        }

        // 4. Calculate total amount
        double totalAmount =
                vehicle.getPricePerDay() * rental.getRentalDays();

        rental.setTotalAmount(totalAmount);

        // 5. Set rental status
        rental.setStatus("RENTED");

        // 6. Change vehicle status
        vehicle.setStatus("RENTED");

        // 7. Save vehicle
        vehicleRepository.save(vehicle);

        // 8. Save rental
        return rentalRepository.save(rental);
    }

    // Get all rentals
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Return a rented vehicle
    public Rental returnVehicle(Long rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() ->
                    new RentalNotFoundException(
                        "Rental with ID " + rentalId + " not found"));

        if (!rental.getStatus().equalsIgnoreCase("RENTED")) {
            throw new IllegalStateException(
                    "Rental with ID " + rentalId + " is already returned");
        }

        Vehicle vehicle = vehicleRepository.findById(rental.getVehicleId())
                .orElseThrow(() ->
                    new VehicleNotFoundException(
                        "Vehicle with ID " + rental.getVehicleId() + " not found"));

        rental.setStatus("RETURNED");

        vehicle.setStatus("AVAILABLE");

        vehicleRepository.save(vehicle);

        return rentalRepository.save(rental);
    }
 // Get rental history of a customer
    public List<Rental> getRentalsByCustomer(Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(
                    "Customer with ID " + customerId + " not found");
        }

        return rentalRepository.findByCustomerId(customerId);
}
}    
    
    
    
    
    
    