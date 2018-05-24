package com.xgh.model.query.specie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecieProjector {
    @Autowired
    private SpecieRepository specieRepository;

    public Specie execute(com.xgh.model.command.valueobjects.Name specie) {
        Specie specieProjection = new Specie(
                specie.toString());
        specieRepository.save(specieProjection);

        return specieProjection;
    }
}
