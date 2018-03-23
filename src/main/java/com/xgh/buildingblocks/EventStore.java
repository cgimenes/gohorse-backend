package com.xgh.buildingblocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventStream;
import com.xgh.valueobjects.EntityId;

public abstract class EventStore<EntityType extends DomainEntity<?>> {
    protected abstract List<Event<?>> getEvents(EntityId id);

	protected abstract void saveEvent(Event<?> event, String entityType);

    public EntityType pull(EntityId id) {
    	List<Event<?>> events = this.getEvents(id);

    	String entityType = this.getEntityType();
    	try {
        	EntityType entity = this.instanceEntity(entityType);    
        	invokeEntityReconstituteMethod(entity, events);
        	return entity;
    	} catch (Exception e) {
            throw new RuntimeException("Não foi possível instanciar a entidade: " + entityType, e);
        }
    }

    @Transactional
	public void push(DomainEntity<?> entity) {
    	EventStream uncommittedEvents = entity.getUncommittedEvents();
    	while (uncommittedEvents.hasNext()) {
    		Event<?> event = uncommittedEvents.next();
    		this.saveEvent(event, entity.getType());
    		EventBus.dispatch(event);
    	}
    }

	protected void invokeEntityReconstituteMethod(EntityType entity, List<Event<?>> events) throws NoSuchMethodException, SecurityException, 
																								 IllegalAccessException, IllegalArgumentException, 
																								 InvocationTargetException {
		Method handlerMethod =  entity.getClass().getDeclaredMethod("reconstitute");
        handlerMethod.setAccessible(true);
  	  	handlerMethod.invoke(entity, new EventStream(events));
	}
    
    @SuppressWarnings("unchecked")
    protected String getEntityType()
    {
        return ((Class<EntityType>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]).getTypeName();
    }

	@SuppressWarnings("unchecked")
	protected EntityType instanceEntity(String entityType) throws ClassNotFoundException, NoSuchMethodException, 
    															SecurityException, InstantiationException, 
    															IllegalAccessException, IllegalArgumentException, 
    															InvocationTargetException {
		Class<DomainEntity<?>> c = (Class<DomainEntity<?>>) Class.forName(entityType);
    	Constructor<DomainEntity<?>> cons = c.getConstructor();
    	return (EntityType) cons.newInstance();
    }
}
