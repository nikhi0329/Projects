package com.vehiclerental.vehicle_rental_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehiclerental.vehicle_rental_system.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByTypeIgnoreCase(String type);
    List<Vehicle> findByStatusIgnoreCase(String status);
    List<Vehicle> findByBrandIgnoreCase(String brand);
    List<Vehicle> findByPricePerDayBetween(
            double minPrice, double maxPrice);
    List<Vehicle> findByModelIgnoreCase(String model);
    List<Vehicle> findAllByOrderByPricePerDayAsc();
    List<Vehicle> findAllByOrderByPricePerDayDesc();
    long countByStatusIgnoreCase(String status);
    
}

