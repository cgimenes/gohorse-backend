package com.xgh.buildingblocks;

import java.lang.reflect.Method;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xgh.valueobjects.EntityVersion;
import com.xgh.valueobjects.Id;

abstract public class Entity<IdType extends Id> {

    protected IdType id;
    
    @JsonIgnore
    private EventStream uncommittedEvents = new EventStream();

	protected EntityVersion version;
    
    public Entity() {
    	this.version = new EntityVersion(0);
    }

    public IdType getId() {
        return id;
    }
    
    protected void recordAndApply(Event<?> event) {
    	this.record(event);
    	this.apply(event);
    	this.version = event.getEntityVersion();
    }
    
    protected EntityVersion nextVersion() {
    	return this.getVersion().next();
    }
    
    private void apply(Event<?> event) {
	    Method handlerMethod = getHandlerMethod(event);
	    if (handlerMethod != null) {
            handlerMethod.setAccessible(true);
	        try {
	    	  handlerMethod.invoke(this, event);
	        } catch (Exception e) {
	            throw new RuntimeException("Não foi possível invocar o método de aplicação do evento: " + event.getClass().getName(), e);
	        }
	    }
    }

	private Method getHandlerMethod(Event<?> event) {
	    try {
	        return getClass().getDeclaredMethod("when", event.getClass());
	    } catch (NoSuchMethodException e) {
	        return null;
	    }
	}

	private void record(Event<?> event) {
    	this.uncommittedEvents = this.uncommittedEvents.add(event);
    }
    
    public EventStream getUncommittedEvents() {
        return uncommittedEvents;
    }

    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (id == null)
            return false;
        Entity<IdType> other = (Entity<IdType>) obj;
        return id.equals(other.getId());
    }

	public EntityVersion getVersion() {
		return version;
	}
	
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
