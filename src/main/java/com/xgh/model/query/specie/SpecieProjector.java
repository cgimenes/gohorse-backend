package com.xgh.model.query.specie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.model.command.valueobjects.Name;

@Component
public class SpecieProjector {
	@Autowired
    private SpecieRepository specieRepository;

    public com.xgh.model.query.specie.Specie execute(Name specie) {
        com.xgh.model.query.specie.Specie specieProjection = new com.xgh.model.query.specie.Specie(
        		specie);
        specieRepository.save(specieProjection);

        return specieProjection;
    }

}
