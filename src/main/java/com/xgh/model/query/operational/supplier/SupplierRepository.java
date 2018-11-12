package com.xgh.model.query.operational.supplier;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends BasicJpaRepository<Supplier, UUID> {

	Page<Supplier> findByNameContainingIgnoreCase(Pageable pageable, String name);
}