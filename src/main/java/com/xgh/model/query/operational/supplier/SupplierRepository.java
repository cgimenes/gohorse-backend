package com.xgh.model.query.operational.supplier;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends BasicJpaRepository<Supplier, UUID> {
}