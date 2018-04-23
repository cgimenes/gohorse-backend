package com.xgh.test.model.command.supplier;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.supplier.Supplier;
import com.xgh.model.command.supplier.SupplierId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CommandSupplierSampleData")
public class SupplierSampleData {
    @Autowired
    private EventStore eventStore;

    public Supplier getSample() {
        Supplier supplier = new Supplier();
        //supplier.register(new SupplierId());
        eventStore.push(supplier);
        return supplier;
    }
}
