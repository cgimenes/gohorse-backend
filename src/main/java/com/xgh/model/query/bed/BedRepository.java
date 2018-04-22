package com.xgh.model.query.bed;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.xgh.infra.repository.BasicJpaRepository;

@Repository
public interface BedRepository extends BasicJpaRepository<Bed, UUID>{

}
