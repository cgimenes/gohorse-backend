package com.xgh.model.query.laboratory;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends BasicJpaRepository<Laboratory, UUID> {
}