package com.xgh.model.query.laboratory;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LaboratoryRepository extends BasicJpaRepository<Laboratory, UUID> {
}