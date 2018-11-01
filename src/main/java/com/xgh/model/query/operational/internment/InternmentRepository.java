package com.xgh.model.query.operational.internment;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface InternmentRepository extends BasicJpaRepository<Internment, UUID> {
}
