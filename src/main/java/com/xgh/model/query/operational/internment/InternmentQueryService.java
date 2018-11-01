package com.xgh.model.query.operational.internment;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternmentQueryService extends BasicQueryService<Internment, InternmentRepository> {
    @Autowired
    protected InternmentQueryService(InternmentRepository repository) {
        super(repository);
    }
}
