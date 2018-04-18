package com.xgh.model.query.owner;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerQueryController extends BasicQueryController<Owner, OwnerQueryService> {
    @Autowired
    public OwnerQueryController(OwnerQueryService service) {
        super(service);
    }
}
