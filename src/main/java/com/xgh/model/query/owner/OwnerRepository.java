package com.xgh.model.query.owner;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerRepository extends BasicJpaRepository<Owner, UUID> {
}
