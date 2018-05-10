package com.xgh.test.model.command.supplier;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.SupplierId;
import com.xgh.model.command.valueobjects.CpfCnpj;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.test.model.command.valueobjects.AddressSampleData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CommandSupplierSampleData")
public class SupplierSampleData {
    @Autowired
    private EventStore eventStore;
    
    @Autowired
    private AddressSampleData address;
    
    public Supplier getSample() {
        Supplier supplier = new Supplier();
        supplier.register( new SupplierId(), new Name("Nestle"),new Phone("44999999999"), new CpfCnpj("00000000191"), address.getSample(), new Name("Ração"));
        eventStore.push(supplier);
        return supplier;
    }
}
