package com.xgh.test.model.query.supplier;

import com.xgh.model.query.supplier.Supplier;
import com.xgh.model.query.supplier.SupplierRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.test.model.query.address.AddressSampleData;
import com.xgh.test.model.query.distributionType.DistributionTypeSampleData;

import java.util.UUID;

@Component("QuerySupplierSampleData")
public class SupplierSampleData {
    private final SupplierRepository supplierRepository;

    @Autowired
    protected SupplierSampleData(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    @Autowired
    private AddressSampleData addressSampleData;
    @Autowired
    private DistributionTypeSampleData distributionTypeSampleData;
    

    public Supplier getSample() {
        Supplier supplier = new Supplier(UUID.randomUUID(), "Nestle", "44998015821",
                "00000000191", addressSampleData.getSample(), distributionTypeSampleData.getSample(), false);
        supplierRepository.save(supplier);
        return supplier;
    }
}
