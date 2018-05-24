package com.xgh.model.query.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BreedProjector {
    @Autowired
    private BreedRepository breedRepository;

    public Breed execute(com.xgh.model.command.valueobjects.Name breed) {
        Breed breedProjection = new Breed(
                breed.toString());
        breedRepository.save(breedProjection);

        return breedProjection;
    }
}
