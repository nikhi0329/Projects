
package com.vehiclerental.vehicle_rental_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vehiclerental.vehicle_rental_system.entity.Vehicle;
import com.vehiclerental.vehicle_rental_system.exception.VehicleNotFoundException;
import com.vehiclerental.vehicle_rental_system.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Add a new vehicle
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Update an existing vehicle
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {

        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                    new VehicleNotFoundException(
                        "Vehicle with ID " + id + " not found"));

        existingVehicle.setVehicleNumber(vehicle.getVehicleNumber());
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setType(vehicle.getType());
        existingVehicle.setPricePerDay(vehicle.getPricePerDay());
        existingVehicle.setStatus(vehicle.getStatus());

        return vehicleRepository.save(existingVehicle);
    }

    // Delete a vehicle
    public void deleteVehicle(Long id) {

        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException(
                    "Vehicle with ID " + id + " not found");
        }

        vehicleRepository.deleteById(id);
    }

    // Search vehicles by type
    public List<Vehicle> getVehiclesByType(String type) {
        return vehicleRepository.findByTypeIgnoreCase(type);
    }

    // Get only available vehicles
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByStatusIgnoreCase("AVAILABLE");
    }
 // Search vehicles by brand
    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrandIgnoreCase(brand);
    }
    
 // Search vehicles by price range
    public List<Vehicle> getVehiclesByPriceRange(
            double minPrice, double maxPrice) {

        if (minPrice < 0 || maxPrice < minPrice) {
            throw new IllegalArgumentException(
                    "Invalid price range");
        }

        return vehicleRepository.findByPricePerDayBetween(
                minPrice, maxPrice);
    }
    public List<Vehicle> getVehiclesByModel(String model) {
        return vehicleRepository.findByModelIgnoreCase(model);
    }
    public List<Vehicle> getVehiclesSortedByPrice() {
        return vehicleRepository.findAllByOrderByPricePerDayAsc();
    }
    public List<Vehicle> getVehiclesSortedByPriceDescending() {
        return vehicleRepository.findAllByOrderByPricePerDayDesc();
    }
    public Page<Vehicle> getVehiclesWithPagination(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(
                        "Vehicle with ID " + id + " not found"));
    }
    public long getVehicleCount() {
        return vehicleRepository.count();
    }
    public long getAvailableVehicleCount() {
        return vehicleRepository.countByStatusIgnoreCase("AVAILABLE");
    }
    public long getRentedVehicleCount() {
        return vehicleRepository.countByStatusIgnoreCase("RENTED");
    }
    public List<Vehicle> getVehiclesByStatus(String status) {
        return vehicleRepository.findByStatusIgnoreCase(status);
    }
    
}
