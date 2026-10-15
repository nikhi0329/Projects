package com.vehiclerental.vehicle_rental_system.exception;

public class RentalNotFoundException extends RuntimeException {

    public RentalNotFoundException(String message) {
        super(message);
    }
}