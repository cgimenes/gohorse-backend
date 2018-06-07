package com.xgh.model.query.supplier;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends BasicJpaRepository<Supplier, UUID> {
}
