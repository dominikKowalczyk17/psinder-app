package com.dkowalczyk.psinder_app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDogRequest {
    @NotBlank(message = "Dog name is required")
    private String name;
    
    @Min(value = 0, message = "Dog age cannot be negative")
    private int age;
    
    @NotBlank(message = "Dog size is required (SMALL, MEDIUM, LARGE)")
    private String size;
    
    @NotBlank(message = "Dog energy is required (SMALL, MEDIUM, HIGH, VERY_HIGH)")
    private String energy;
    private String breed;
    private String bio;
    private List<String> photos;
}
