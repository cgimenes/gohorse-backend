package com.xgh.model.query.internment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xgh.infra.service.BasicQueryService;

@Service
public class InternmentQueryService extends BasicQueryService<Internment, InternmentRepository> {
    @Autowired
    protected InternmentQueryService(InternmentRepository repository) {
        super(repository);
    }
}
