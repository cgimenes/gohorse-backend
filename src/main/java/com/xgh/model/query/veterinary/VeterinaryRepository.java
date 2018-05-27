package com.xgh.model.query.veterinary;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VeterinaryRepository extends BasicJpaRepository<Veterinary, UUID> {
}
