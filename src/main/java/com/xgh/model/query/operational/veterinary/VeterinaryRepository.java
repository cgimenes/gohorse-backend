package com.xgh.model.query.operational.veterinary;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryRepository extends BasicJpaRepository<Veterinary, UUID> {
}
