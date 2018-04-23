package com.xgh.model.query.supplier;

<<<<<<< HEAD
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
	Optional<Supplier> findOneByIdAndDeletedFalse(UUID id);

	Page<Supplier> findByDeletedFalse(Pageable pageable);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
>>>>>>> origin/master
}
