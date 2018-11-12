package com.xgh.test.model.query.operational.sex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.enumerator.EnumeratorRepository;

@Component("QuerySexSampleData")
public class SexSampleData {
	private final EnumeratorRepository sexRepository;

    @Autowired
    protected SexSampleData(EnumeratorRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    public Enumerator getSample() {
        Enumerator sex = new Enumerator();
        sexRepository.save(sex);
        return sex;
    }
}
