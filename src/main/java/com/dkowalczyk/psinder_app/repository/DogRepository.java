package com.dkowalczyk.psinder_app.repository;

import com.dkowalczyk.psinder_app.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByName(String name);

    List<Dog> findBySize(Dog.DogSize size);

    List<Dog> findByEnergyBetween(Dog.EnergyLevel minEnergy, Dog.EnergyLevel maxEnergy);
}
