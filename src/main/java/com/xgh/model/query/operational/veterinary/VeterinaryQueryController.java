package com.xgh.model.query.operational.veterinary;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veterinarians")
public class VeterinaryQueryController extends BasicQueryController<Veterinary, VeterinaryQueryService> {
    @Autowired
    public VeterinaryQueryController(VeterinaryQueryService repository) {
        super(repository);
    }
}
