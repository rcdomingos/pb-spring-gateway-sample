package com.compass.pb.demos.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "pet-service")
public interface PetClient {

    @GetMapping(value = "/pet/{id}")
    Map<Object, String> getPet(@PathVariable String id);

    @GetMapping(value = "/pet/{id}/vacinas")
    Map<Object, String> getPetVaciona(@PathVariable String id);


}
