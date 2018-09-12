package com.xgh.test.model.query.operational.specie;

import com.xgh.model.query.operational.specie.Specie;
import com.xgh.model.query.operational.specie.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySpecieSampleData")
public class SpecieSampleData {
    private final SpecieRepository specieRepository;

    @Autowired
    protected SpecieSampleData(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public Specie getSample() {
        Specie specie = new Specie("Catioro");
        specieRepository.save(specie);
        return specie;
    }
}
