package com.xgh.model.query.distributionType;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionTypeRepository extends JpaRepository<DistributionType, UUID> {
}