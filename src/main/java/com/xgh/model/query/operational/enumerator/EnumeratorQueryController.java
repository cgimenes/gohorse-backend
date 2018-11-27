package com.xgh.model.query.operational.enumerator;

import com.xgh.infra.controller.BasicQueryController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enumerators")
public class EnumeratorQueryController extends BasicQueryController<Enumerator, EnumeratorQueryService> {
    @Autowired
    public EnumeratorQueryController(EnumeratorQueryService service) {
        super(service);
    }

    @GetMapping("/all")
    public ResponseEntity<ArrayList<EnumeratorGroup>> findAllEnumerators() {
    	ArrayList<EnumeratorGroup> enumerators = this.service.findAllGroupingByKind();
        return ResponseEntity.ok().body(enumerators);
    }
    
    @GetMapping("/find")
    public ResponseEntity<List<Enumerator>> findByType(@RequestParam(name = "type") String type,
                                                  @RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        List<Enumerator> page = this.service.findByDeletedFalseAndKindContainingIgnoreCase(pageNumber, type);
        return ResponseEntity.ok().body(page);
    }
}
