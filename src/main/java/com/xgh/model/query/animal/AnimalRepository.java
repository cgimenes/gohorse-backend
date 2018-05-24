package com.xgh.model.query.animal;

import java.util.UUID;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends BasicJpaRepository<Animal, UUID> {
}
