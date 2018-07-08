package com.xgh.model.query.additionalregister;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class AdditionalRegisterQueryController extends BasicQueryController<AdditionalRegister, AdditionalRegisterQueryService> {
    @Autowired
    public AdditionalRegisterQueryController(AdditionalRegisterQueryService service) {
        super(service);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<AdditionalRegister>> findByType(@RequestParam(name = "type") String type,
                                                  @RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<AdditionalRegister> page = this.service.findByNameContainingIgnoreCase(pageNumber, type);
        return ResponseEntity.ok().body(page);
    }
}
