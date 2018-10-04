package com.xgh.model.query.operational.enumerators;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumeratorRepository extends BasicJpaRepository<Enumerator, UUID> {
    Page<Enumerator> findByKindContainingIgnoreCase(Pageable pageable, String kind);
}
