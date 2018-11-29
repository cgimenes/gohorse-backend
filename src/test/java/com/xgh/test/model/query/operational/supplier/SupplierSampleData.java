package com.xgh.test.model.query.operational.supplier;

import com.xgh.model.query.operational.supplier.Supplier;
import com.xgh.model.query.operational.supplier.SupplierRepository;
import com.xgh.test.model.query.operational.address.AddressSampleData;
import java.util.UUID;

import com.xgh.test.model.query.operational.enumerator.EnumeratorSampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySupplierSampleData")
public class SupplierSampleData {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressSampleData addressSampleData;

    private EnumeratorSampleData distTypeSampleData;

    @Autowired
    protected SupplierSampleData(SupplierRepository supplierRepository, EnumeratorSampleData distTypeSampleData) {
        this.supplierRepository = supplierRepository;
        this.distTypeSampleData = distTypeSampleData;
    }

    public Supplier getSample() {
        Supplier supplier = new Supplier(UUID.randomUUID(), "Nestle", "44998015821",
                "00000000191", addressSampleData.getSample(), distTypeSampleData.getSample(),false);
        supplierRepository.save(supplier);
        return supplier;
    }
}
