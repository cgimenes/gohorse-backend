package com.xgh.model.query.veterinary;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinaryQueryService extends BasicQueryService<Veterinary, VeterinaryRepository> {
    @Autowired
    public VeterinaryQueryService(VeterinaryRepository repository) {
        super(repository);
    }
}
