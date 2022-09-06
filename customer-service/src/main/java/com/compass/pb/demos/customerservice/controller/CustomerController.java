package com.compass.pb.demos.customerservice.controller;

import com.compass.pb.demos.customerservice.client.PetClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final PetClient petClient;

    @Operation(summary = "lista um customer", description = "Communicates with the partner API to create an order.")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomers() {
        log.info("Get Customers");

        Map<String, Object> customer1 = new HashMap<>();
        customer1.put("id", "1");
        customer1.put("nome", "Joao");
        customer1.put("pet", "cachorro");

        Map<String, Object> customer2 = new HashMap<>();
        customer2.put("id", "1");
        customer2.put("nome", "Joao");
        customer2.put("pet", "cachorro");

        return ResponseEntity.ok(Arrays.asList(customer1, customer2));
    }


    @Operation(summary = "lista um customer pelo id", description = "Listar um customer pelo informado.")
    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerById(
            @Parameter(name = "id", required = true, description = "ID of the client. e.g.: 1")  @PathVariable String id) {
        log.info("Get Customers");
        Map<String, Object> customer2 = new HashMap<>();
        customer2.put("id", id);
        customer2.put("nome", "Joao");

        Map<Object, String> pet = petClient.getPet(id);
        customer2.put("pet",pet);


        return ResponseEntity.ok(customer2);
    }
}
