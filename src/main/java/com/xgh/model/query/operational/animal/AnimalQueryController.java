package com.xgh.model.query.operational.animal;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalQueryController extends BasicQueryController<Animal, AnimalQueryService> {
    @Autowired
    protected AnimalQueryController(AnimalQueryService service) {
        super(service);
    }
}
