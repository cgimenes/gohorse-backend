package com.xgh.buildingblocks;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.buildingblocks.event.EventStream;
import com.xgh.exceptions.EntityNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public abstract class EventStore {
    protected abstract <T extends AggregateRoot<?>> List<EntityEvent<?>> getEvents(Class<T> entityType, EntityId id);

    protected abstract void saveEvent(EntityEvent<?> event, String entityType);

    public abstract <T extends AggregateRoot<?>> boolean entityExists(Class<T> entityType, EntityId id);

    /*
     * Retorna uma entidade, realizando a reconstituição da mesma à partir de seus eventos
     */
    public <T extends AggregateRoot<?>> T pull(Class<T> entityType, EntityId id) {
        List<EntityEvent<?>> events = this.getEvents(entityType, id);

        if (events.isEmpty()) {
            throw new EntityNotFoundException(entityType.getSimpleName(), id.getValue());
        }

        T entity;
        try {
            entity = this.instanceEntity(entityType);
            invokeEntityReconstituteMethod(entity, events);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Não foi possível instanciar a entidade: %s", entityType), e);
        }

        return entity;
    }

    /*
     * Persiste os eventos que ainda não foram persistidos e
     * dispara os event handlers para cada evento
     */
    @Transactional
    public void push(AggregateRoot<?> entity) {
        EventStream uncommittedEvents = entity.getUncommittedEvents();
        while (uncommittedEvents.hasNext()) {
            EntityEvent<?> event = uncommittedEvents.next();
            this.saveEvent(event, entity.getType());
            EventBus.dispatch(event);
        }
    }

    /*
     * Instancia a entidade e a reconstitui à partir dos seus eventos, invocando o método "reconstitute" usando reflection
     */
    private void invokeEntityReconstituteMethod(AggregateRoot<?> entity, List<EntityEvent<?>> events)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        Class<?> clazz = entity.getClass();
        while (true) {
            try {
                Method method = clazz.getDeclaredMethod("reconstitute", EventStream.class);
                method.setAccessible(true);
                method.invoke(entity, new EventStream(events));
                return;
            } catch (NoSuchMethodException ex) {
                clazz = clazz.getSuperclass();
                if (clazz == null) {
                    throw new NoSuchMethodException(String.format(
                            "Método 'reconstitute' não declarado na classe: %s", entity.getClass().getName()));
                }
            }
        }
    }

    /*
     * Instancia a entidade usando reflection
     */
    private <T extends AggregateRoot<?>> T instanceEntity(Class<T> entityType) throws
            NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        return entityType.getConstructor().newInstance();
    }
}
