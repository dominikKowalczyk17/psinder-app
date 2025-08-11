package com.dkowalczyk.psinder_app.service;

import com.dkowalczyk.psinder_app.dto.*;
import com.dkowalczyk.psinder_app.model.Dog;
import com.dkowalczyk.psinder_app.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogRepository dogRepository;

    @Transactional(readOnly = true)
    public List<DogDto> getAllDogs() {
        return dogRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DogDto getDogById(Long id) {
        Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dog not found with id: " + id));
        return convertToDto(dog);
    }

    @Transactional(readOnly = true)
    public List<DogDto> getDogsBySize(String size) {
        Dog.DogSize dogSize = Dog.DogSize.valueOf(size.toUpperCase());
        return dogRepository.findBySize(dogSize)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DogDto> getDogsByEnergyRange(String minEnergy, String maxEnergy) {
        Dog.EnergyLevel dogMinEnergy = Dog.EnergyLevel.valueOf(minEnergy.trim().toUpperCase());
        Dog.EnergyLevel dogMaxEnergy = Dog.EnergyLevel.valueOf(maxEnergy.trim().toUpperCase());
        return dogRepository.findByEnergyBetween(dogMinEnergy, dogMaxEnergy)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DogDto convertToDto(Dog dog) {
        DogDto dto = new DogDto();
        dto.setId(dog.getId());
        dto.setName(dog.getName());
        dto.setAge(dog.getAge());
        dto.setSize(dog.getSize() != null ? dog.getSize().name() : null);
        dto.setEnergy(dog.getEnergy() != null ? dog.getEnergy().name() : null);
        dto.setBio(dog.getBio());
        dto.setPhotos(dog.getPhotos());
        dto.setBreed(dog.getBreed());
        return dto;
    }

    public Dog convertToEntity(CreateDogRequest request) {
        Dog dog = new Dog();
        dog.setName(request.getName());
        dog.setAge(request.getAge());
        
        // Validate and set size
        try {
            dog.setSize(Dog.DogSize.valueOf(request.getSize().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid dog size: " + request.getSize() + 
                                     ". Valid values are: SMALL, MEDIUM, LARGE");
        }
        
        dog.setEnergy(Dog.EnergyLevel.valueOf(request.getEnergy().toUpperCase()));
        dog.setBio(request.getBio());
        dog.setPhotos(request.getPhotos());
        return dog;
    }
}