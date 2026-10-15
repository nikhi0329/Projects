package com.vehiclerental.vehicle_rental_system.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.vehiclerental.vehicle_rental_system.entity.Vehicle;
import com.vehiclerental.vehicle_rental_system.service.VehicleService;
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "http://localhost:5174"
	})
	
@RestController
@RequestMapping("/api/vehicles")

public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
    
    // Update an existing vehicle
    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id,
                                 @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }
 // Delete a vehicle
    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully";
    }
 // Get vehicles by type
    @GetMapping("/type/{type}")
    public List<Vehicle> getVehiclesByType(@PathVariable String type) {
        return vehicleService.getVehiclesByType(type);
    }
 // Get available vehicles
    @GetMapping("/available")
    public List<Vehicle> getAvailableVehicles() {
        return vehicleService.getAvailableVehicles();
    }
 // Search vehicles by brand
    @GetMapping("/brand/{brand}")
    public List<Vehicle> getVehiclesByBrand(
            @PathVariable String brand) {
        return vehicleService.getVehiclesByBrand(brand);
    }
    
 // Search vehicles by price range
    @GetMapping("/price-range")
    public List<Vehicle> getVehiclesByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return vehicleService.getVehiclesByPriceRange(
                minPrice, maxPrice);
    }
    @GetMapping("/model/{model}")
    public List<Vehicle> getVehiclesByModel(@PathVariable String model) {
        return vehicleService.getVehiclesByModel(model);
    }
    @GetMapping("/sort-by-price")
    public List<Vehicle> getVehiclesSortedByPrice() {
        return vehicleService.getVehiclesSortedByPrice();
    }
    @GetMapping("/sort-by-price-desc")
    public List<Vehicle> getVehiclesSortedByPriceDescending() {
        return vehicleService.getVehiclesSortedByPriceDescending();
    }
    @GetMapping("/page")
    public Page<Vehicle> getVehiclesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
    	if (page < 0) {
            throw new IllegalArgumentException(
                    "Page number cannot be negative");
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100");
        }

        Pageable pageable = PageRequest.of(page, size);
        return vehicleService.getVehiclesWithPagination(pageable);
    }
    
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }
    @GetMapping("/count")
    public long getVehicleCount() {
        return vehicleService.getVehicleCount();
    }
    @GetMapping("/available/count")
    public long getAvailableVehicleCount() {
        return vehicleService.getAvailableVehicleCount();
    }
    @GetMapping("/rented/count")
    public long getRentedVehicleCount() {
        return vehicleService.getRentedVehicleCount();
    }
    @GetMapping("/status/{status}")
    public List<Vehicle> getVehiclesByStatus(
            @PathVariable String status) {
        return vehicleService.getVehiclesByStatus(status);
    }
    
}