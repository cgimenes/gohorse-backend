package com.xgh.model.query.operational.laboratory;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryQueryService extends BasicQueryService<Laboratory, LaboratoryRepository> {
    @Autowired
    public LaboratoryQueryService(LaboratoryRepository repository) {
        super(repository);
    }
}
