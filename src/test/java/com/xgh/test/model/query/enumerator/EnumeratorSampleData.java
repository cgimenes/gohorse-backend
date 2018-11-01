package com.xgh.test.model.query.enumerator;

import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("QueryEnumeratorSampleData")
public class EnumeratorSampleData {
    @Autowired
    private EnumeratorRepository enumeratorRepository;

    public Enumerator getSample() {
        Enumerator enumerators = new Enumerator(UUID.randomUUID(), "Raça", "Xitzu", false, false);
        enumeratorRepository.save(enumerators);
        return enumerators;
    }
}
