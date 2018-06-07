package com.xgh.model.query.product;

import com.xgh.infra.repository.BasicJpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BasicJpaRepository<Product, UUID> {
}
