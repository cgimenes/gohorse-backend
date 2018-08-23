package com.xgh.model.query.enumerators;

import com.xgh.infra.controller.BasicQueryController;

import java.util.HashMap;

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
    public ResponseEntity<HashMap<String,Enumerator>> findAllEnumerators() {
    	HashMap<String,Enumerator> enumerators = this.service.findAllGroupingByKind();
        return ResponseEntity.ok().body(enumerators);
    }
    
    @GetMapping("/find")
    public ResponseEntity<Page<Enumerator>> findByType(@RequestParam(name = "type") String type,
                                                  @RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<Enumerator> page = this.service.findByTypeContainingIgnoreCase(pageNumber, type);
        return ResponseEntity.ok().body(page);
    }
}
