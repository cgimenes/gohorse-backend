package com.xgh.test.model.query.owner;

import com.xgh.model.query.address.Address;
import com.xgh.model.query.owner.Owner;
import com.xgh.model.query.owner.OwnerRepository;
import com.xgh.test.model.query.address.AddressSampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component("QueryOwnerSampleData")
public class OwnerSampleData {
    private final OwnerRepository supplierRepository;
    private final AddressSampleData addressSampleData;

    @Autowired
    protected OwnerSampleData(OwnerRepository supplierRepository, AddressSampleData addressSampleData) {
        this.supplierRepository = supplierRepository;
        this.addressSampleData = addressSampleData;
    }

    public Owner getSample() {
        Address address = addressSampleData.getSample();
        Owner owner = new Owner(UUID.randomUUID(), "Dono Master", "09450600929", "44313371337", LocalDate.parse("1911-01-01"), address, false);
        supplierRepository.save(owner);
        return owner;
    }
}
