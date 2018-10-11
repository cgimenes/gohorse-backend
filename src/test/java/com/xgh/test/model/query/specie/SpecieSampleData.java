package com.xgh.test.model.query.specie;

import com.xgh.model.query.enumerators.Enumerator;
import com.xgh.model.query.enumerators.EnumeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySpecieSampleData")
public class SpecieSampleData {
	private final EnumeratorRepository specieRepository;

    @Autowired
    protected SpecieSampleData(EnumeratorRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public Enumerator getSample() {
        Enumerator specie = new Enumerator();
        specieRepository.save(specie);
        return specie;
    }
}
