package com.xgh.test.model.query.breed;

import com.xgh.model.query.enumerators.Enumerator;
import com.xgh.model.query.enumerators.EnumeratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QueryBreedSampleData")
public class BreedSampleData {
	private final EnumeratorRepository breedRepository;

    @Autowired
    protected BreedSampleData(EnumeratorRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public Enumerator getSample() {
        Enumerator breed = new Enumerator();
        breedRepository.save(breed);
        return breed;
    }
}
