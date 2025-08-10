package com.dkowalczyk.psinder_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String name;
    private int age;
    private String bio;
    private CreateDogRequest dog;
}
