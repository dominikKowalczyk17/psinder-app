package com.dkowalczyk.psinder_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "dogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "breed")
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private DogSize size;

    @Column(name = "energy_level")
    private EnergyLevel energy;

    @Column(name = "bio", length = 500)
    private String bio;

    @ElementCollection
    @CollectionTable(name = "dog_photos", joinColumns = @JoinColumn(name = "dog_id"))
    @Column(name = "photo_url")
    private List<String> photos;

    public enum DogSize {
        SMALL, MEDIUM, LARGE
    }

    public enum EnergyLevel {
        LOW, MEDIUM, HIGH, VERY_HIGH
    }
}
