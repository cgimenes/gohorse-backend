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

@MappedSuperclass
abstract public class AggregateRoot<IdType extends EntityId> extends DomainEntity<IdType> {
	/*
	 * Eventos que foram gerados durante um ciclo de execução e que faltam ser persistidos
	 */
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

    /*
     * Grava o novo evento na lista de eventos à serem persistidos (uncommittedEvents)
     * e aplica o evento
     * 
     * TODO preecher o version com o nextVersion automagicamente 
     */
    protected void recordAndApply(Event<IdType> event) {
    	if (this.isDeleted()) {
    		throw new DeletedEntityException();
    	}
    	
    	this.record(event);
    	this.apply(event);
    }

    /*
     * Retorna a próxima versão do agregado
     */
    protected EntityVersion nextVersion() {
    	return this.getVersion().next();
    }

    /*
     * Aplica o evento, atualizando os metadados e invocando o handler do evento 
     */
    private void apply(Event<IdType> event) {
    	this.updateMetadata(event);
	    this.invokeHandlerMethod(event);
    }

	private void updateMetadata(Event<IdType> event) {
	    this.id = event.getEntityId();
		this.version = event.getEntityVersion();
	}

	/*
	 * Encontra e executa o handler do evento
	 */
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

	/*
	 * Encontra o handler do evento (método 'when' com o argumento do mesmo tipo do evento)
	 */
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

    /*
     * Aplica todos os eventos do event stream na entidade limpa
     */
	@SuppressWarnings("unchecked")
	protected void reconstitute(EventStream events) {
		if (!this.version.isBlank()) {
			throw new RuntimeException("Só é possível reconstituir à partir de uma entidade sem alterações");
		}
		
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
