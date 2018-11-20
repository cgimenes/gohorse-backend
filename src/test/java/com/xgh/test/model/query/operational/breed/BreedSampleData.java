package com.xgh.test.model.query.operational.breed;

import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;

import java.util.UUID;

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
        Enumerator breed = new Enumerator(UUID.randomUUID(),"Raça","Tremedeira",false,false);
        breedRepository.save(breed);
        return breed;
    }
}
