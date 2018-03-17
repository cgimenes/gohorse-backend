package com.xgh.buildingblocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.xgh.valueobjects.Id;

// TODO: Rever o local dessa implementação (usar default method?)
public abstract class EventStore<IdType extends Id, EntityType extends Entity<IdType>> {
    @Autowired
    protected JdbcTemplate connection;
    
    public boolean snapshotExists(IdType id) {
    	Integer cnt = connection.queryForObject(
    		    "select count(1) from " + this.getSnapshotTableName() + " where id = ?", 
    		    Integer.class,
    		    id.toString());
		return cnt != null && cnt > 0;
    }

    @Transactional
	public void push(EntityType entity) {
    	EventStream uncommittedEvents = entity.getUncommittedEvents();
    	while (uncommittedEvents.hasNext()) {
    		Event<?> event = uncommittedEvents.next();
    		this.executePush(event, entity.getType());
    		EventBus.dispatch(event);
    	}
    	this.saveSnapshot(entity);
    }
    
    // TODO event sourcing
    public EntityType pull(IdType id) {
    	return null;
    }
    
    private void executePush(Event<?> event, String entityType) {
    	connection.update(
		    "insert into event ("
		    + "entity_id, entity_version, entity_type, event_type, ocurred_on, event_data"
		    + ") VALUES (?, ?, ?, ?, ?, cast(? as json))",
		    event.getEntityId().toString(),
		    event.getEntityVersion().getValue(),
		    entityType,
		    event.getType(),
		    event.getOcurredOn(),
	    	event.toString()
		);
    }
    
    protected abstract void saveSnapshot(EntityType entity);
    
    protected abstract String getSnapshotTableName();
}
