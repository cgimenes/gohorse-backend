package com.xgh.test.model.query.operational.supplier;

import com.xgh.model.query.operational.enumerator.Enumerator;
import com.xgh.model.query.operational.supplier.Supplier;
import com.xgh.model.query.operational.supplier.SupplierRepository;
import com.xgh.test.model.query.enumerator.DistributionTypeSampleData;
import com.xgh.test.model.query.operational.address.AddressSampleData;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySupplierSampleData")
public class SupplierSampleData {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressSampleData addressSampleData;
    
    private DistributionTypeSampleData distTypeSampleData;  

    public Supplier getSample() {
    	Enumerator distributionType = distTypeSampleData.getSample();
    	
        Supplier supplier = new Supplier(UUID.randomUUID(), "Nestle", "44998015821", distributionType,
                "00000000191", addressSampleData.getSample(), false);
        supplierRepository.save(supplier);
        return supplier;
    }
}
