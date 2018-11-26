package com.xgh.test.model.command.operational.supplier;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.supplier.Supplier;
import com.xgh.model.command.operational.supplier.SupplierId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.model.command.operational.enumerator.Enumerator;
import com.xgh.test.model.command.enumerator.DistributionTypeSampleData;
import com.xgh.test.model.command.operational.valueobjects.AddressSampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CommandSupplierSampleData")
public class SupplierSampleData {
    @Autowired
    private EventStore eventStore;

    @Autowired
    private AddressSampleData address;
    
    @Autowired
    private DistributionTypeSampleData distTypeSampleData;

    public Supplier getSample() {
    	Enumerator distributionType = distTypeSampleData.getSample();
    	
        Supplier supplier = new Supplier();
        supplier.register(
                new SupplierId(),
                new Name("Mercadão de Maringá"),
                new Phone("44999999999"),
                distributionType.getId(),
                "00000000191",
                address.getSample()
        );
        eventStore.push(supplier);
        return supplier;
    }
}
