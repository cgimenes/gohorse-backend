package com.xgh.model.query.internment;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InternmentRepository extends BasicJpaRepository<Internment, UUID> {
}
