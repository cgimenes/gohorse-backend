package com.xgh.buildingblocks;

import java.lang.reflect.Method;

import com.fasterxml.jackson.annotation.JsonIgnore;

abstract public class Entity<IdType extends EntityId> {

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
    }
    
    protected EntityVersion nextVersion() {
    	return this.getVersion().next();
    }
    
    private void apply(Event<?> event) {
    	this.updateMetadata(event);
	    this.invokeHandlerMethod(event);
    }

	@SuppressWarnings("unchecked")
	private void updateMetadata(Event<?> event) {
	    this.id = (IdType) event.getEntityId();
		this.version = event.getEntityVersion();
	}

	private void invokeHandlerMethod(Event<?> event) {
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
    	this.uncommittedEvents.add(event);
    }
    
    public EventStream getUncommittedEvents() {
        return uncommittedEvents;
    }
        
	protected void reconstitute(EventStream events) {
    	while (events.hasNext()) {
			this.apply(events.next());
    	}    	
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
		return this.getClass().getName();
	}
}
