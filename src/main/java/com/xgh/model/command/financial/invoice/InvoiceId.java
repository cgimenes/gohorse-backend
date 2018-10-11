package com.xgh.model.command.financial.invoice;

import com.xgh.buildingblocks.entity.EntityId;
import java.util.UUID;

public class InvoiceId extends EntityId {
    public InvoiceId(UUID id) {
        super(id);
    }

    public InvoiceId() {
        super();
    }

    public InvoiceId(String id) {
        super(id);
    }
}
