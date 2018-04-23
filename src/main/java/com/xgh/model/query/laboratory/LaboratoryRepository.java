package com.xgh.model.query.laboratory;

import java.util.UUID;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends BasicJpaRepository<Laboratory, UUID> {
}