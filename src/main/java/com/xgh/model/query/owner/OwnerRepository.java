package com.xgh.model.query.owner;

import com.xgh.infra.repository.BasicJpaRepository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends BasicJpaRepository<Owner, UUID> {
    //@Query("select id,name from owner where name like %?1%")
    //List<Owner> findByName(String name);
	
	Page<Owner> findByNameContainingIgnoreCase(Pageable pageable, String name);
}
