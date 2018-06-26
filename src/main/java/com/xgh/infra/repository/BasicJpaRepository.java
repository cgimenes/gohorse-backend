package com.xgh.infra.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicJpaRepository<EntityT, IdT> extends JpaRepository<EntityT, IdT> {
	Page<EntityT> findByDeletedFalse(Pageable pageable);

    Optional<EntityT> findOneByIdAndDeletedFalse(IdT id);   
}
