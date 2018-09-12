package com.xgh.model.query.operational.product;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductQueryController extends BasicQueryController<Product, ProductQueryService> {
    @Autowired
    public ProductQueryController(ProductQueryService service) {
        super(service);
    }
}
