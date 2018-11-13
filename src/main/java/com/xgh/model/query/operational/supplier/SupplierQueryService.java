package com.xgh.model.query.operational.supplier;

import com.xgh.Constants;
import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SupplierQueryService extends BasicQueryService<Supplier, SupplierRepository> {
    @Autowired
    public SupplierQueryService(SupplierRepository repository) {
        super(repository);
    }

	public Page<Supplier> findByNameContainingIgnoreCase(int page, String name) {
		PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByNameContainingIgnoreCase(request,name);
	}
}
