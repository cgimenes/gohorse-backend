package com.xgh.model.query.internment;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xgh.infra.repository.BasicJpaRepository;

@Repository
public interface InternmentRepository extends BasicJpaRepository<Internment, UUID> {
}
