package com.xgh.model.query.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BreedProjector {
    @Autowired
    private BreedRepository breedRepository;

    public com.xgh.model.query.breed.Breed execute(Breed breed) {
        com.xgh.model.query.breed.Breed breedProjection = new com.xgh.model.query.breed.Breed(
        		breed.getId(),
        		breed.getName());
        breedRepository.save(breedProjection);

        return breedProjection;
    }
}
