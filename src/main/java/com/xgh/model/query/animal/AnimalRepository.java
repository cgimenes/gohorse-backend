package com.xgh.model.query.animal;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimalRepository extends BasicJpaRepository<Animal, UUID> {
}
