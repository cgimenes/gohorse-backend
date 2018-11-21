package com.xgh.model.query.operational.internment;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internments")
public class InternmentQueryController extends BasicQueryController<Internment, InternmentQueryService> {
    @Autowired
    protected InternmentQueryController(InternmentQueryService service) {
        super(service);
    }

    @GetMapping("/lasttwelvemonths")
    public ResponseEntity<List<Internment>> findLastTwelveMonths() {
        List<Internment> appointments = this.service.findLastTwelveMonths();
        return ResponseEntity.ok().body(appointments);

    }

}
