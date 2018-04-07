package com.xgh.xgh.veterinary.query;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryQueryRepository extends JpaRepository<Veterinary, UUID> {
}
