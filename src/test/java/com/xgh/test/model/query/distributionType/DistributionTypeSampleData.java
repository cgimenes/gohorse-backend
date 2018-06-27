package com.xgh.test.model.query.distributionType;

import com.xgh.model.query.distributionType.DistributionType;
import com.xgh.model.query.distributionType.DistributionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistributionTypeSampleData {

    @Autowired
    private DistributionTypeRepository distributionTypeRepository;

    public DistributionType getSample() {
        DistributionType distributionType = new DistributionType("Ração");
        distributionTypeRepository.save(distributionType);
        return distributionType;
    }
}
