package com.app.apicustomers.controllers;

import com.app.apicustomers.domain.Customer;
import com.app.apicustomers.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" }) //http://localhost:4200/clientes
@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer", description = "customer related operations")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findCustomers(){
        return customerService.findCustomers();
    }

    //getCustomerbyID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerByID(@PathVariable(value = "id")
                                                         Integer customerID){
        return customerService.findCustomerByID(customerID);
    }

    @Operation(summary = "Create Customer",
            description = "Create a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "400", description = "Customer not created",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody  Customer newCustomer){

        return this.customerService.createCustomer(newCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerByID(@PathVariable(value = "id")
                                                    Integer customerID){
        return customerService.deleteCustomerByID(customerID);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomerByID(@RequestBody Customer updatedCustomer){
        return customerService.updateCustomerByID(updatedCustomer);
    }



}
