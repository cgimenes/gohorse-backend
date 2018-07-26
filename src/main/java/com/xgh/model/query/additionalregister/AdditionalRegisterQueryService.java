package com.xgh.model.query.additionalregister;

import com.xgh.Constants;
import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AdditionalRegisterQueryService extends BasicQueryService<AdditionalRegister, AdditionalRegisterRepository> {
    @Autowired
    public AdditionalRegisterQueryService(AdditionalRegisterRepository repository) {
        super(repository);
    }
    
    public Page<AdditionalRegister> findByTypeContainingIgnoreCase(int page, String type) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByTypeContainingIgnoreCase(request,type);
    }
}
