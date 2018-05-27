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
public abstract class BasicQueryService<Entity, Repository extends BasicJpaRepository<Entity, UUID>> {
    private final Repository repository;

    protected BasicQueryService(Repository repository) {
        this.repository = repository;
    }

    public Page<Entity> findAll(int page) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findByDeletedFalse(request);
    }

    public Entity findById(UUID id) {
        Optional<Entity> entity = repository.findOneByIdAndDeletedFalse(id);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(getEntityName());
        }
        return entity.get();
    }

    @SuppressWarnings("unchecked")
    private String getEntityName() {
        return ((Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
