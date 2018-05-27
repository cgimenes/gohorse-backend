package com.xgh.infra.repository;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Repository
public class PostgresEventStore extends EventStore {
    // TODO migrar para Mongo
    private final JdbcTemplate connection;

    private final RowMapper<Event<?>> eventRowMapper = (rs, rowNum) -> {
        Calendar ocurredOn = Calendar.getInstance();
        ocurredOn.setTime(rs.getDate("ocurred_on"));

        return Event.fromJson(
                rs.getString("event_type"),
                UUID.fromString(rs.getString("entity_id")),
                new EntityVersion(rs.getInt("entity_version")),
                ocurredOn,
                rs.getString("event_data"));
    };

    @Autowired
    public PostgresEventStore(JdbcTemplate connection) {
        this.connection = connection;
    }

    @Override
    protected <T extends AggregateRoot<?>> List<Event<?>> getEvents(Class<T> entityType, EntityId id) {
        return connection.query(
                "select entity_id, entity_version, entity_type, event_type, ocurred_on, event_data "
                        + "from event "
                        + "where entity_id = ? "
                        + "and entity_type = ? "
                        + "order by entity_version asc",
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
                        + ") VALUES (?, ?, ?, ?, ?, ?)",
                event.getEntityId().getValue(),
                event.getEntityVersion().getValue(),
                entityType,
                event.getType(),
                event.getOcurredOn(),
                event.toJson()
        );
    }

    @Override
    public <T extends AggregateRoot<?>> boolean entityExists(Class<T> entityType, EntityId id) {
        Integer count = connection.queryForObject(
                "select count(1) from event where entity_id = ? and entity_type = ?",
                Integer.class, id.getValue(), entityType.getName());
        return count != null && count > 0;
    }
}
