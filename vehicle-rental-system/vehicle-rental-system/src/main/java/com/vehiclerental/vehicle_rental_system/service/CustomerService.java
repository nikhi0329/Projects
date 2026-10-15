package com.vehiclerental.vehicle_rental_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vehiclerental.vehicle_rental_system.entity.Customer;
import com.vehiclerental.vehicle_rental_system.exception.CustomerNotFoundException;
import com.vehiclerental.vehicle_rental_system.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Update an existing customer
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() ->
                    new CustomerNotFoundException(
                        "Customer with ID " + id + " not found"));

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setLicenseNumber(customer.getLicenseNumber());

        return customerRepository.save(existingCustomer);
    }

    // Delete a customer
    public void deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(
                    "Customer with ID " + id + " not found");
        }

        customerRepository.deleteById(id);
    }
}