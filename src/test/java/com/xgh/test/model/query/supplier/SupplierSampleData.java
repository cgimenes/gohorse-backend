package com.xgh.test.model.query.supplier;

import com.xgh.model.query.supplier.Supplier;
import com.xgh.model.query.supplier.SupplierRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySupplierSampleData")
public class SupplierSampleData {
    private final SupplierRepository supplierRepository;

    @Autowired
    protected SupplierSampleData(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier getSample() {
        Supplier supplier = new Supplier(UUID.randomUUID());
        supplierRepository.save(supplier);
        return supplier;
    }
}
