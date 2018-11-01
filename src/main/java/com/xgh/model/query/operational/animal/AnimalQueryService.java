package com.xgh.model.query.operational.animal;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalQueryService extends BasicQueryService<Animal, AnimalRepository> {
    @Autowired
    protected AnimalQueryService(AnimalRepository repository) {
        super(repository);
    }
}
