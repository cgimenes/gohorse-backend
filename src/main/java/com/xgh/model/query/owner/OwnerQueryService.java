package com.xgh.model.query.owner;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerQueryService extends BasicQueryService<Owner, OwnerRepository> {
    @Autowired
    public OwnerQueryService(OwnerRepository repository) {
        super(repository);
    }
}
