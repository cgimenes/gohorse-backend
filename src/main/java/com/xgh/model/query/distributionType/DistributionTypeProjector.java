package com.xgh.model.query.distributionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistributionTypeProjector {

	@Autowired
	private DistributionTypeRepository distributionTypeRepository;

	public DistributionType execute(com.xgh.model.command.valueobjects.Name distributionType) {

		DistributionType distributionTypeProjection = new DistributionType(distributionType.getValue());

		distributionTypeRepository.save(distributionTypeProjection);

		return distributionTypeProjection;
	}

}
