package com.xgh.buildingblocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

// TODO: Passar essa implementação para a infra
public abstract class EventStore<IdType extends EntityId, EntityType extends Entity<IdType>> {

	// TODO: Criar classe que encapsula essa conexão
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
    	List<Event<?>> events = connection.query(
    		"select entity_id, entity_version, entity_type, event_type, ocurred_on, event_data "
    		+ "from event "
    		+ "where entity_id = ? "
    		+ "and entity_type = ?", 
    		new EventRowMapper(),
    		id.toString(),
    		this.getEntityType());

    	String entityType = this.getEntityType();
    	try {
        	EntityType entity = this.instanceEntity(entityType);    
        	invokeEntityReconstituteMethod(entity, events);
        	return entity;
    	} catch (Exception e) {
            throw new RuntimeException("Não foi possível instanciar a entidade: " + entityType, e);
        }
    }

	private void invokeEntityReconstituteMethod(EntityType entity, List<Event<?>> events) throws NoSuchMethodException, SecurityException, 
																								 IllegalAccessException, IllegalArgumentException, 
																								 InvocationTargetException {
		Method handlerMethod =  entity.getClass().getDeclaredMethod("reconstitute");
        handlerMethod.setAccessible(true);
  	  	handlerMethod.invoke(entity, new EventStream(events));
	}
    
    @SuppressWarnings("unchecked")
	private String getEntityType()
    {
        return ((Class<EntityType>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]).getTypeName();
    }

	@SuppressWarnings("unchecked")
    private EntityType instanceEntity(String entityType) throws ClassNotFoundException, NoSuchMethodException, 
    															SecurityException, InstantiationException, 
    															IllegalAccessException, IllegalArgumentException, 
    															InvocationTargetException {
		Class<Entity<?>> c = (Class<Entity<?>>) Class.forName(entityType);
    	Constructor<Entity<?>> cons = c.getConstructor();
    	return (EntityType) cons.newInstance();
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

	private final class EventRowMapper implements RowMapper<Event<?>> {
    	@Override
    	public Event<?> mapRow(ResultSet rs, int rowNum) throws SQLException {
    		return null;
//    		return (Event<?>) Event.fromString(rs.getString("event_type"), rs.getString("event_data"));
    	}
    }
}
