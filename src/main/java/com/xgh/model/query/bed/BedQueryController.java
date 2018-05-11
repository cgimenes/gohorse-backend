package com.xgh.model.query.bed;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bed")
public class BedQueryController extends BasicQueryController<Bed, BedQueryService> {
    @Autowired
    public BedQueryController(BedQueryService service) {
        super(service);
    }
}
