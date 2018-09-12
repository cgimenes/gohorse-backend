package com.xgh.model.query.operational.owner;

import com.xgh.Constants;
import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OwnerQueryService extends BasicQueryService<Owner, OwnerRepository> {
    @Autowired
    public OwnerQueryService(OwnerRepository repository) {
        super(repository);
    }
    
    public Page<Owner> findByNameContainingIgnoreCase(int page, String name) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByNameContainingIgnoreCase(request,name);
    }
}
