package com.dkowalczyk.psinder_app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    @Min(value = 1, message = "Age must be at least 1")
    private int age;
    
    private String bio;
    
    @NotNull(message = "Dog information is required for registration")
    @Valid
    private CreateDogRequest dog;
}
