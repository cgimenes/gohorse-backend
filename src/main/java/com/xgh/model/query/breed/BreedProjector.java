package com.xgh.model.query.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.model.command.valueobjects.Name;

@Component
public class BreedProjector {
    @Autowired
    private BreedRepository breedRepository;

    public com.xgh.model.query.breed.Breed execute(Name breed) {
        com.xgh.model.query.breed.Breed breedProjection = new com.xgh.model.query.breed.Breed(
        		breed);
        breedRepository.save(breedProjection);

        return breedProjection;
    }
}
