package com.xgh.model.query.owner;

import java.util.UUID;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends BasicJpaRepository<Owner, UUID> {
}
