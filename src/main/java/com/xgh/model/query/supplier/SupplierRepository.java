package com.xgh.model.query.supplier;
 
import java.util.UUID;
import com.xgh.infra.repository.BasicJpaRepository; 
import org.springframework.stereotype.Repository; 
 
@Repository
public interface SupplierRepository extends BasicJpaRepository<Supplier, UUID> {
}