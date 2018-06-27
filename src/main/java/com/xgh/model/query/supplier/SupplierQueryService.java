package com.xgh.model.query.supplier;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierQueryService extends BasicQueryService<Supplier, SupplierRepository> {
    @Autowired
    public SupplierQueryService(SupplierRepository repository) {
        super(repository);
    }
}
