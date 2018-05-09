package com.xgh.model.query.supplier;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierQueryController extends BasicQueryController<Supplier, SupplierQueryService> {
    @Autowired
    public SupplierQueryController(SupplierQueryService service) {
        super(service);
    }
}
