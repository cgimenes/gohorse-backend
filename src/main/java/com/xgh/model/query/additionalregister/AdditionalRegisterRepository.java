package com.xgh.model.query.additionalregister;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalRegisterRepository extends BasicJpaRepository<AdditionalRegister, UUID> {
    Page<AdditionalRegister> findByTypeContainingIgnoreCase(Pageable pageable, String type);
}
