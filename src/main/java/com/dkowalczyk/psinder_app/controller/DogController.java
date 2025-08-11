package com.dkowalczyk.psinder_app.controller;

import com.dkowalczyk.psinder_app.dto.DogDto;
import com.dkowalczyk.psinder_app.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DogDto>> getAllDogs() {
        List<DogDto> dogs = dogService.getAllDogs();
        return ResponseEntity.ok(dogs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<DogDto> getDogById(@PathVariable Long id) {
        DogDto dog = dogService.getDogById(id);
        return ResponseEntity.ok(dog);
    }

    @GetMapping("/by-size/{size}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DogDto>> getDogsBySize(@PathVariable String size) {
        List<DogDto> dogs = dogService.getDogsBySize(size);
        return ResponseEntity.ok(dogs);
    }

    @GetMapping("/by-energy")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<DogDto>> getDogsByEnergyRange(
            @RequestParam String minEnergy,
            @RequestParam String maxEnergy) {
        List<DogDto> dogs = dogService.getDogsByEnergyRange(minEnergy, maxEnergy);
        return ResponseEntity.ok(dogs);
    }
}