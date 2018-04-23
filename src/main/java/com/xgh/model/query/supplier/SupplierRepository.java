package com.xgh.model.query.supplier;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends BasicJpaRepository<Supplier, UUID> {
}
