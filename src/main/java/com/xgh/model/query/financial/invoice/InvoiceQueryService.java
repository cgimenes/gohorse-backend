package com.xgh.model.query.financial.invoice;

import com.xgh.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class InvoiceQueryService {
    private final InvoiceRepository repository;

    @Autowired
    protected InvoiceQueryService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public Page<Invoice> findAllCreated(int page) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findAllByStatus(request, "CREATED");
    }
}
