package com.dkowalczyk.psinder_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogDto {
    private Long id;
    private String name;
    private int age;
    private String breed;
    private String size;
    private String energy;
    private String bio;
    private List<String> photos;
}
