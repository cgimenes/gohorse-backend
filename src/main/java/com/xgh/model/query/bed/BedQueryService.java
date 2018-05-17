package com.xgh.model.query.bed;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedQueryService extends BasicQueryService<Bed, BedRepository> {
    @Autowired
    public BedQueryService(BedRepository repository) {
        super(repository);
    }
}
