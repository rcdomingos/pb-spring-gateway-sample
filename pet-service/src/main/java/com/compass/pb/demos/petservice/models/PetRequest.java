package com.compass.pb.demos.petservice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PetRequest {

    @Schema(required = true, description = "name the animal is used to")
    @NotNull
    @Size(min = 2, max=50)
    private String name;

    @Schema(example = "Dog", required = true, description = "")
    @NotNull
    private String type;
}
