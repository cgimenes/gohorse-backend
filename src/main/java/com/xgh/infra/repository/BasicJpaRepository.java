package com.xgh.infra.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicJpaRepository<Entity, ID> extends JpaRepository<Entity, ID> {
    Page<Entity> findByDeletedFalse(Pageable pageable);

    Optional<Entity> findOneByIdAndDeletedFalse(ID id);
}
