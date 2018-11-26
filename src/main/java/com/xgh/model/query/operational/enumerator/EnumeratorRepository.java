package com.xgh.model.query.operational.enumerator;

import com.xgh.infra.repository.BasicJpaRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumeratorRepository extends BasicJpaRepository<Enumerator, UUID> {
    List<Enumerator> findByDeletedFalseAndKindContainingIgnoreCase(String kind);
}
