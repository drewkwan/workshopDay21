package vttp.workshopPrac.day21Workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.workshopPrac.day21Workshop.models.Customer;
import vttp.workshopPrac.day21Workshop.models.Orders;
import vttp.workshopPrac.day21Workshop.repositories.CustomersRepository;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersRESTController {

    @Autowired
    private CustomersRepository custRepo;

    @GetMapping(path="/customers") 
    public ResponseEntity<String> getAllCustomers() {

        int limit = 5;
        int offset= 0;

        //Query the db for the Customers
        List<Customer> customers = custRepo.getAllCustomers(limit, offset);

        //Build the result
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Customer c: customers) {
            arrBuilder.add(c.toJson());
        }
        JsonArray result = arrBuilder.build();
        
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }

    @GetMapping(path="/customers/{limit}/{offset}") 
    public ResponseEntity<String> getAllCustomersLimitOffset(@PathVariable int limit, @PathVariable int offset) {


        //Query the db for the Customers
        List<Customer> customers = custRepo.getAllCustomers(limit, offset);

        //Build the result
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Customer c: customers) {
            arrBuilder.add(c.toJson());
        }
        JsonArray result = arrBuilder.build();
        
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }

    @GetMapping(path="/customer/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable int id) {
        //query the db
        List<Customer> customers = custRepo.getCustomerById(id);
        //build result
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Customer c: customers) {
            arrayBuilder.add(c.toJson());
        }
        JsonArray result = arrayBuilder.build();

        if (result.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                        .add("error", "Id %d is not found".formatted(id))
                        .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }

    }


    @GetMapping(path="/customer/{id}/orders") 
    public ResponseEntity<String> getOrdersByCustId(@PathVariable int id) {

        //Perform the query
        List<Orders> orders = custRepo.getOrdersById(id);
        //build the result
        JsonArrayBuilder arrayBuilder =Json.createArrayBuilder();
        for (Orders o: orders) {
            arrayBuilder.add(o.toJson());
        }

        JsonArray result = arrayBuilder.build();

        if (result.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                        .add("error", "No orders associated with this id".formatted(id))
                        .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
        }

    }
    
}
