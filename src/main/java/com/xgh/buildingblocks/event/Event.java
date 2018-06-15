package com.xgh.buildingblocks.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgh.ApplicationContextProvider;
import com.xgh.buildingblocks.entity.EntityId;
import com.xgh.buildingblocks.entity.EntityVersion;
import com.xgh.buildingblocks.valueobject.ValueObject;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.UUID;

public abstract class Event<IdT extends EntityId> implements ValueObject {
    @JsonIgnore
    private Calendar ocurredOn;
    @JsonIgnore
    private IdT entityId;
    @JsonIgnore
    private EntityVersion entityVersion;

    protected Event() {
    }

    protected Event(IdT entityId, EntityVersion entityVersion) {
        this.entityId = entityId;
        this.entityVersion = entityVersion;
        this.ocurredOn = Calendar.getInstance();
    }

    public IdT getEntityId() {
        return this.entityId;
    }

    public Calendar getOcurredOn() {
        return ocurredOn;
    }

    public EntityVersion getEntityVersion() {
        return entityVersion;
    }

    @JsonIgnore
    public String getType() {
        return this.getClass().getName();
    }

    /*
     * Deserializa um evento à partir de um JSON
     */
    public static Event<?> fromJson(
            String eventType,
            UUID entityId,
            EntityVersion entityVersion,
            Calendar ocurredOn,
            String data) {
        try {
            Event<?> event = (Event<?>) unserialize(eventType, data);

            Class<?> entityIdType = ((Class<?>) ((ParameterizedType) event.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0]);

            event.entityVersion = entityVersion;
            event.ocurredOn = ocurredOn;

            Field field = getEntityIdField(event);
            field.set(event, entityIdType.getConstructor(UUID.class).newInstance(entityId));

            return event;
        } catch (Exception e) {
            throw new RuntimeException(String.format(
                    "Não foi possível deserializar o evento: %s - entityId = %s; entityVersion = %s; ocurredOn = %s; data = %s",
                    eventType,
                    entityId,
                    entityVersion,
                    ocurredOn.getTime(),
                    data), e);
        }
    }

    /*
     * Deserializa um objeto à partir de um JSON
     */
    private static ValueObject unserialize(String type, String json) {
        ObjectMapper mapper = ApplicationContextProvider.getApplicationContext().getBean(ObjectMapper.class);
        try {
            return (ValueObject) mapper.readValue(json, Class.forName(type));
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(String.format(
                "Falha ao deserializar objeto do tipo: %s, com os dados: %s", type, json), e);
        }
    }

    /*
     * Obtem o campo "entityId" através de reflection
     */
    private static Field getEntityIdField(Event<?> event) throws NoSuchFieldException {
        Field field;
        Class<?> clazz = event.getClass();
        while (true) {
            try {
                field = clazz.getDeclaredField("entityId");
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
                if (clazz == null) {
                    throw new NoSuchFieldException(String.format(
                            "Campo 'entityId' não declarado na classe: %s", event.getClass().getName()));
                }
            }
        }
        return field;
    }
}
