package com.xgh.buildingblocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventStream;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.valueobjects.EntityId;

public abstract class EventStore {
    protected abstract <T extends DomainEntity<?>> List<Event<?>> getEvents(Class<T> entityType, EntityId id);

	protected abstract void saveEvent(Event<?> event, String entityType);

    public <T extends DomainEntity<?>> T pull(Class<T> entityType, EntityId id) {
    	List<Event<?>> events = this.getEvents(entityType, id);
    	
    	if (events.isEmpty()) {
            throw new EntityNotFoundException();
    	}

    	try {    		
    		T entity = this.instanceEntity(entityType);    
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

	protected void invokeEntityReconstituteMethod(DomainEntity<?> entity, List<Event<?>> events) throws NoSuchMethodException, SecurityException, 
																								 IllegalAccessException, IllegalArgumentException, 
																								 InvocationTargetException {	
		Class<?> clazz = entity.getClass();
		while (true) {
			try {
			    Method method = clazz.getDeclaredMethod("reconstitute", EventStream.class);				
			    method.setAccessible(true);
			    method.invoke(entity, new EventStream(events));
			    return;
			} catch(NoSuchMethodException ex) {
			    clazz = clazz.getSuperclass();
			    if (clazz == null) {
			    	throw new NoSuchMethodException(
			    			"Método 'reconstitute' não declarado na classe: " + entity.getClass().getName());
			    }
			}
		}
	}

	protected <T extends DomainEntity<?>> T instanceEntity(Class<T> entityType) throws ClassNotFoundException, 
																NoSuchMethodException, SecurityException, 
																InstantiationException, IllegalAccessException, 
																IllegalArgumentException, InvocationTargetException {
    	return entityType.getConstructor().newInstance();
    }
}
