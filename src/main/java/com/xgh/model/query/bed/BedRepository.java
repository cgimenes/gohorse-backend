package com.xgh.model.query.bed;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends BasicJpaRepository<Bed, UUID> {
}
