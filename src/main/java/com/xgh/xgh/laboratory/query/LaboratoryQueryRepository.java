package com.xgh.xgh.laboratory.query;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryQueryRepository extends JpaRepository<Laboratory, UUID> {
}