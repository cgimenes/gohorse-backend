package com.xgh.infra.service;

import com.xgh.Constants;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.infra.repository.BasicJpaRepository;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// TODO logar
public abstract class BasicQueryService<EntityT, RepositoryT extends BasicJpaRepository<EntityT, UUID>> {
    private final RepositoryT repository;

    protected BasicQueryService(RepositoryT repository) {
        this.repository = repository;
    }

    public Page<EntityT> findAll(int page) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findByDeletedFalse(request);
    }

    public EntityT findById(UUID id) {
        Optional<EntityT> entity = repository.findOneByIdAndDeletedFalse(id);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(getEntityName());
        }
        return entity.get();
    }

    @SuppressWarnings("unchecked")
    private String getEntityName() {
        return ((Class<EntityT>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
