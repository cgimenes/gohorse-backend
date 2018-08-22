package com.xgh.model.query.enumerators;

import com.xgh.Constants;
import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EnumeratorQueryService extends BasicQueryService<Enumerator, EnumeratorRepository> {
    @Autowired
    public EnumeratorQueryService(EnumeratorRepository repository) {
        super(repository);
    }
    
    public Page<Enumerator> findByTypeContainingIgnoreCase(int page, String kind) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByKindContainingIgnoreCase(request,kind);
    }
}
