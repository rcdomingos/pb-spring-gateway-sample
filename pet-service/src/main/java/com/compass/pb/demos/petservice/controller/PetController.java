package com.compass.pb.demos.petservice.controller;

import com.compass.pb.demos.petservice.models.DefaultError;
import com.compass.pb.demos.petservice.models.PetRequest;
import com.compass.pb.demos.petservice.models.PetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pet")
@Tag(name = "pet", description = "the pet API")
public class PetController {

    @Operation(summary = "Get all pets", description = "Get a list of pets", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class)))
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PetResponse>> getPet() {
        log.info("Get petz");

        PetResponse pet1 = new PetResponse();
        pet1.setId(new Date().getTime());
        pet1.setName("Pictuchu");
        pet1.setType("Dog");

        PetResponse pet2 = new PetResponse();
        pet2.setId(new Date().getTime());
        pet2.setName("Sr Miau");
        pet2.setType("Cat");

        return ResponseEntity.ok(Arrays.asList(pet1, pet2));
    }


    @Operation(summary = "Get a pet", description = "Get a pet by id", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "Validation exception", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class)))
    })
    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PetResponse> getPetById(@Parameter(description = "ID of pet to return", required = true) @PathVariable String id) {
        log.info("Get petz by Id {}", id);
        PetResponse pet1 = new PetResponse();
        pet1.setId(new Date().getTime());
        pet1.setName("Pictuchu");
        pet1.setType("Dog");

        return ResponseEntity.ok(pet1);
    }

    @Operation(summary = "Add a new pet to the store", description = "Add a new pet to the store", tags = {"pet"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PetResponse.class))),
            @ApiResponse(responseCode = "405", description = "Invalid input",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DefaultError.class))
            )
    })
    @PostMapping(consumes = "application/json")
    public ResponseEntity<PetResponse> addPet(
            @Parameter(description = "Create a new pet in the store", required = true) @Valid @RequestBody PetRequest pet) throws URISyntaxException {
        PetResponse petCreated = new PetResponse();
        petCreated.setName(pet.getName());
        petCreated.setType(pet.getType());
        petCreated.setId(new Date().getTime());

        URI localhost = new URI("localhost/");

        return ResponseEntity.created(localhost).body(petCreated);

    }

}
