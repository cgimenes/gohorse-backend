package com.xgh.infra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.DomainEntity;
import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventStore;
import com.xgh.valueobjects.EntityId;
import com.xgh.valueobjects.EntityVersion;

@Component
public class PostgresEventStore<EntityType extends DomainEntity<?>> extends EventStore {
    @Autowired
    protected JdbcTemplate connection;
    
	@Override
    protected <T extends DomainEntity<?>> List<Event<?>> getEvents(Class<T> entityType, EntityId id) {
    	return connection.query(
        		"select entity_id, entity_version, entity_type, event_type, ocurred_on, event_data "
        		+ "from event "
        		+ "where entity_id = ? "
        		+ "and entity_type = ?", 
        		new EventRowMapper(),
        		id.toString(),
        		entityType.getName());
    }
    
	// TODO tratar exceção de concorrência
	@Override
	protected void saveEvent(Event<?> event, String entityType) {
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
	
	private final class EventRowMapper implements RowMapper<Event<?>> {
    	@Override
    	public Event<?> mapRow(ResultSet rs, int rowNum) throws SQLException {
    		Calendar ocurredOn = Calendar.getInstance();
    		ocurredOn.setTime(rs.getDate("ocurred_on"));
    		
    		return Event.fromString(
    				rs.getString("event_type"),
    				UUID.fromString(rs.getString("entity_id")),
    				new EntityVersion(rs.getInt("entity_version")),
    				ocurredOn,
    				rs.getString("event_data"));
    	}
    }
}
