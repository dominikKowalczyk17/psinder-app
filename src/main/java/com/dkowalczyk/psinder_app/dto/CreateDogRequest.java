package com.dkowalczyk.psinder_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDogRequest {
    private String name;
    private int age;
    private String size;
    private int energy;
    private String bio;
    private List<String> photos;
}
