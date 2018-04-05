package com.xgh.buildingblocks;

import java.lang.reflect.Method;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.xgh.exceptions.DeletedEntityException;
import com.xgh.valueobjects.EntityId;
import com.xgh.valueobjects.EntityVersion;

// TODO comentar o buildingblocks
@MappedSuperclass
abstract public class AggregateRoot<IdType extends EntityId> extends DomainEntity<IdType> {
    @Transient
    private EventStream uncommittedEvents = new EventStream();
    
    @Column(name = "deleted")
    private Boolean deleted = false;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "entity_version"))
	protected EntityVersion version;

    public AggregateRoot() {
    	this.version = new EntityVersion(0);
    }

    // TODO preecher o version com o nextVersion automagicamente 
    protected void recordAndApply(Event<IdType> event) {
    	if (this.isDeleted()) {
    		throw new DeletedEntityException();
    	}
    	
    	this.record(event);
    	this.apply(event);
    }

    protected EntityVersion nextVersion() {
    	return this.getVersion().next();
    }

    private void apply(Event<IdType> event) {
    	this.updateMetadata(event);
	    this.invokeHandlerMethod(event);
    }

	private void updateMetadata(Event<IdType> event) {
	    this.id = event.getEntityId();
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

	@SuppressWarnings("unchecked")
	protected void reconstitute(EventStream events) {
    	while (events.hasNext()) {
			this.apply((Event<IdType>) events.next());
    	}
    }

	public EntityVersion getVersion() {
		return version;
	}

	public Boolean isDeleted() {
		return deleted;
	}
	
	protected void markDeleted() {
		this.deleted = true;
	}
}
