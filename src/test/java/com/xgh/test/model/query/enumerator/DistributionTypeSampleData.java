package com.xgh.test.model.query.enumerator;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;

@Component("QueryDistributionTypeSampleData")
public class DistributionTypeSampleData {
	@Autowired
    private EnumeratorRepository enumeratorRepository;

    public Enumerator getSample() {
        Enumerator enumerators = new Enumerator(UUID.randomUUID(), "Tipo de Distribuição", "Atacado", false, false);
        enumeratorRepository.save(enumerators);
        return enumerators;
    }

}
