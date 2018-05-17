package com.xgh.model.query.bed;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BedRepository extends BasicJpaRepository<Bed, UUID> {
}
