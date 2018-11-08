package com.xgh.model.query.operational.bed;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends BasicJpaRepository<Bed, UUID> {
}
