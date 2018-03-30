package com.xgh.buildingblocks;

import java.lang.reflect.Method;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.xgh.exceptions.DeletedEntityException;
import com.xgh.valueobjects.EntityId;
import com.xgh.valueobjects.EntityVersion;

// TODO comentar o buildingblocks
@MappedSuperclass
abstract public class DomainEntity<IdType extends EntityId> {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    protected IdType id;

    @Transient
    private EventStream uncommittedEvents = new EventStream();
    
    @Transient
    private Boolean deleted = false;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "entity_version"))
	protected EntityVersion version;

    public DomainEntity() {
    	this.version = new EntityVersion(0);
    }

    public IdType getId() {
        return id;
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
        DomainEntity<IdType> other = (DomainEntity<IdType>) obj;
        return id.equals(other.getId());
    }

	public EntityVersion getVersion() {
		return version;
	}

	public String getType() {
		return this.getClass().getName();
	}

	public Boolean isDeleted() {
		return deleted;
	}
	
	protected void markDeleted() {
		this.deleted = true;
	}
}
