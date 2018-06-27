package com.xgh.model.query.owner;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends BasicJpaRepository<Owner, UUID> {
    Page<Owner> findByNameContainingIgnoreCase(Pageable pageable, String name);
}
