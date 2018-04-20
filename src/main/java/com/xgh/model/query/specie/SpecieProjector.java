package com.xgh.model.query.specie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecieProjector {
	@Autowired
    private SpecieRepository specieRepository;

    public com.xgh.model.query.specie.Specie execute(Specie specie) {
        com.xgh.model.query.specie.Specie specieProjection = new com.xgh.model.query.specie.Specie(
        		specie.getId(),
        		specie.getName());
        specieRepository.save(specieProjection);

        return specieProjection;
    }

}
