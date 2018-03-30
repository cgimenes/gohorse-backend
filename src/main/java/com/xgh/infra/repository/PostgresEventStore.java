package com.xgh.infra.repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.xgh.buildingblocks.AggregateRoot;
import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventStore;
import com.xgh.valueobjects.EntityId;
import com.xgh.valueobjects.EntityVersion;

@Repository
public class PostgresEventStore<EntityType extends AggregateRoot<?>, IdType extends EntityId> extends EventStore {
    @Autowired
    protected JdbcTemplate connection;
    
    @Autowired
    protected JpaSnapshotRepository<EntityType, IdType> snapshotRepository;
    
	private final RowMapper<Event<?>> eventRowMapper = (rs, rowNum) -> {
		Calendar ocurredOn = Calendar.getInstance();
		ocurredOn.setTime(rs.getDate("ocurred_on"));
		
		return Event.fromString(
				rs.getString("event_type"),
				UUID.fromString(rs.getString("entity_id")),
				new EntityVersion(rs.getInt("entity_version")),
				ocurredOn,
				rs.getString("event_data"));
	};
    
	@Override
    protected <T extends AggregateRoot<?>> List<Event<?>> getEvents(Class<T> entityType, EntityId id) {
    	return connection.query(
        		"select entity_id, entity_version, entity_type, event_type, ocurred_on, event_data "
        		+ "from event "
        		+ "where entity_id = ? "
        		+ "and entity_type = ?", 
        		eventRowMapper,
        		id.getValue(),
        		entityType.getName());
    }
    
	// TODO tratar exceção de concorrência
	@Override
	protected void saveEvent(Event<?> event, String entityType) {
    	connection.update(
		    "insert into event ("
		    + "entity_id, entity_version, entity_type, event_type, ocurred_on, event_data"
		    + ") VALUES (?, ?, ?, ?, ?, cast(? as json))",
		    event.getEntityId().getValue(),
		    event.getEntityVersion().getValue(),
		    entityType,
		    event.getType(),
		    event.getOcurredOn(),
	    	event.toString()
		);
    }

	@SuppressWarnings("unchecked")
	@Override
	protected void saveSnapshot(AggregateRoot<?> entity) {
		snapshotRepository.save((EntityType) entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void deleteSnapshot(AggregateRoot<?> entity) {
		snapshotRepository.delete((EntityType) entity);
		
	}
}
