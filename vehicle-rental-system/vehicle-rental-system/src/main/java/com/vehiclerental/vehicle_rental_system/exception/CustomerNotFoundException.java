package com.vehiclerental.vehicle_rental_system.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.vehiclerental.vehicle_rental_system.exception.VehicleNotFoundException;
import com.vehiclerental.vehicle_rental_system.exception.CustomerNotFoundException;
import com.vehiclerental.vehicle_rental_system.exception.RentalNotFoundException;
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}