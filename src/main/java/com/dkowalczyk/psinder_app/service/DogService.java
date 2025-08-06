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
    public List<DogDto> getDogsByEnergyRange(int minEnergy, int maxEnergy) {
        return dogRepository.findByEnergyBetween(minEnergy, maxEnergy)
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
        dto.setEnergy(dog.getEnergy());
        dto.setBio(dog.getBio());
        dto.setPhotos(dog.getPhotos());
        return dto;
    }

    public Dog convertToEntity(CreateDogRequest request) {
        Dog dog = new Dog();
        dog.setName(request.getName());
        dog.setAge(request.getAge());
        dog.setSize(Dog.DogSize.valueOf(request.getSize().toUpperCase()));
        dog.setEnergy(request.getEnergy());
        dog.setBio(request.getBio());
        dog.setPhotos(request.getPhotos());
        return dog;
    }
}