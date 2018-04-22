package com.xgh.model.query.veterinary;

import java.util.UUID;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryRepository extends BasicJpaRepository<Veterinary, UUID> {
}
