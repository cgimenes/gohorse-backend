package com.xgh.model.query.operational.specie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecieProjector {
    private final SpecieRepository specieRepository;

    @Autowired
    public SpecieProjector(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public Specie execute(com.xgh.model.command.operational.valueobjects.Name specie) {
        Specie specieProjection = new Specie(
                specie.toString());
        specieRepository.save(specieProjection);

        return specieProjection;
    }
}
