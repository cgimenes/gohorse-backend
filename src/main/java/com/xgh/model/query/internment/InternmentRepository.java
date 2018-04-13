package com.xgh.model.query.internment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternmentRepository extends JpaRepository<Internment, UUID> {

}
