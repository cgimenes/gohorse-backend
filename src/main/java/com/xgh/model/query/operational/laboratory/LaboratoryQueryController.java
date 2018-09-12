package com.xgh.model.query.operational.laboratory;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryQueryController extends BasicQueryController<Laboratory, LaboratoryQueryService> {
    @Autowired
    public LaboratoryQueryController(LaboratoryQueryService service) {
        super(service);
    }
}
