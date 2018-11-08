package com.xgh.model.query.operational.animal;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends BasicJpaRepository<Animal, UUID> {
}
