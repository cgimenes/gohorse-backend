package com.xgh.model.query.owner;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends BasicJpaRepository<Owner, UUID> {
}
