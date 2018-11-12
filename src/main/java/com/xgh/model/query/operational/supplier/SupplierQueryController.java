package com.xgh.model.query.operational.supplier;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierQueryController extends BasicQueryController<Supplier, SupplierQueryService> {
    @Autowired
    public SupplierQueryController(SupplierQueryService service) {
        super(service);
    }
    
    @GetMapping("/find")
    public ResponseEntity<Page<Supplier>> findByName(@RequestParam(name = "name") String name,
                                                  @RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<Supplier> page = this.service.findByNameContainingIgnoreCase(pageNumber, name);
        return ResponseEntity.ok().body(page);
    }
}
