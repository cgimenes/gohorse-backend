package com.xgh.test.model.query.operational.enumerator;

import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("QueryBedSampleData")
public class BedSampleData {
    @Autowired
    private EnumeratorRepository enumeratorRepository;

    public Enumerator getSample() {
        Enumerator enumerators = new Enumerator(UUID.randomUUID(), "Leito", "ABC1234", false, false);
        enumeratorRepository.save(enumerators);
        return enumerators;
    }
}
