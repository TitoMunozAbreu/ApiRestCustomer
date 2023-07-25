package com.app.apicustomers.services;

import com.app.apicustomers.domain.Customer;
import com.app.apicustomers.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public ResponseEntity<List<Customer>> findCustomers() {
       boolean isEmpty = this.customerRepository.findAll().isEmpty();

       if(isEmpty){
           throw  new NoSuchElementException("Customer list not found");
       }

       return ResponseEntity
               .status(HttpStatus.ACCEPTED)
               .body(this.customerRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Customer> findCustomerByID(Integer customerID) {
        Customer customerFound = this.customerRepository.findById(customerID)
                .orElseThrow(() -> new NoSuchElementException("Customer ID: " + customerID + " not found."));

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customerFound);
    }

    @Transactional
    public ResponseEntity<?> createCustomer(Customer newCustomer) {

        this.customerRepository.save(newCustomer);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("{\"mensaje\":\" " + "Customer: " + newCustomer.getFirstName() + " " + newCustomer.getLastName() + " creado con exito.\"}");
    }

    @Transactional
    public ResponseEntity<?> deleteCustomerByID(Integer customerID) {
        Customer customerFound = findCustomerByID(customerID).getBody();

        this.customerRepository.deleteById(customerFound.getId());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("{\"mensaje\":\" " + "Customer: " + customerFound.getFirstName() + " " + customerFound.getLastName() + " eliminado.\"}");
    }

    @Transactional
    public ResponseEntity<?> updateCustomerByID(Customer updatedCustomer) {
        Customer customerFound = findCustomerByID(updatedCustomer.getId()).getBody();

        customerFound.setFirstName(updatedCustomer.getFirstName());
        customerFound.setLastName(updatedCustomer.getLastName());
        customerFound.setEmail(updatedCustomer.getEmail());
        customerFound.setDob(updatedCustomer.getDob());

        this.customerRepository.save(customerFound);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("{\"mensaje\":\" " + "Customer: " + customerFound.getFirstName() + " " + customerFound.getLastName() + " actualizado con exito.\"}");

    }
}
