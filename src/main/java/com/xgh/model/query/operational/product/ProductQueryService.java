package com.xgh.model.query.operational.product;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductQueryService extends BasicQueryService<Product, ProductRepository> {
    @Autowired
    public ProductQueryService(ProductRepository repository) {
        super(repository);
    }
}
