package com.vehiclerental.vehicle_rental_system.entity;
    import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.Positive;

	@Entity
	@Table(name = "vehicles")
	public class Vehicle {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotBlank(message = "Vehicle number is required")
	    private String vehicleNumber;
	    
	    @NotBlank(message = "Brand is required")
	    private String brand;
	    @NotBlank(message = "Model is required")
	    private String model;
	    @NotBlank(message = "Vehicle type is required")
	    private String type;
	    @Positive(message = "Price per day must be greater than 0")
	    private double pricePerDay;
	    @NotBlank(message = "Status is required")
	    private String status;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getVehicleNumber() {
	        return vehicleNumber;
	    }

	    public void setVehicleNumber(String vehicleNumber) {
	        this.vehicleNumber = vehicleNumber;
	    }

	    public String getBrand() {
	        return brand;
	    }

	    public void setBrand(String brand) {
	        this.brand = brand;
	    }

	    public String getModel() {
	        return model;
	    }

	    public void setModel(String model) {
	        this.model = model;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public double getPricePerDay() {
	        return pricePerDay;
	    }

	    public void setPricePerDay(double pricePerDay) {
	        this.pricePerDay = pricePerDay;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	}

