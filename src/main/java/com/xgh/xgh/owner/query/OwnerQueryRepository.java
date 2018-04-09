package com.xgh.xgh.owner.query;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerQueryRepository extends JpaRepository<Owner, UUID> {
}
