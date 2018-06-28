package com.xgh.test.model.query.supplier;

import com.xgh.model.query.supplier.Supplier;
import com.xgh.model.query.supplier.SupplierRepository;
import com.xgh.test.model.query.address.AddressSampleData;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QuerySupplierSampleData")
public class SupplierSampleData {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressSampleData addressSampleData;

    public Supplier getSample() {
        Supplier supplier = new Supplier(UUID.randomUUID(), "Nestle", "44998015821",
                "00000000191", addressSampleData.getSample(), false);
        supplierRepository.save(supplier);
        return supplier;
    }
}
