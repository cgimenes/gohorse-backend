package com.xgh.model.query.product;

import com.xgh.infra.repository.BasicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends BasicJpaRepository<Product, UUID> {
}
