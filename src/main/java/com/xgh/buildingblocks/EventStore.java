package com.xgh.buildingblocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.xgh.buildingblocks.entity.AggregateRoot;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.buildingblocks.event.EventStream;
import com.xgh.exceptions.EntityNotFoundException;
import com.xgh.buildingblocks.entity.EntityId;

public abstract class EventStore {
	protected abstract <T extends AggregateRoot<?>> List<Event<?>> getEvents(Class<T> entityType, EntityId id);

	protected abstract void saveEvent(Event<?> event, String entityType);

	public abstract <T extends AggregateRoot<?>> boolean entityExists(Class<T> entityType, EntityId id);

	/*
	 * Retorna uma entidade, realizando a reconstituição da mesma à partir de seus
	 * eventos
	 */
	public <T extends AggregateRoot<?>> T pull(Class<T> entityType, EntityId id) {
		List<Event<?>> events = this.getEvents(entityType, id);

		if (events.isEmpty()) {
			throw new EntityNotFoundException(entityType.getSimpleName());
		}

		T entity;
		try {
			entity = this.instanceEntity(entityType);
			invokeEntityReconstituteMethod(entity, events);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível instanciar a entidade: " + entityType, e);
		}

		return entity;
	}

	/*
	 * Persiste os eventos que ainda não foram persistidos e dispara os event
	 * handlers para cada evento
	 */
	// TODO transação
	public void push(AggregateRoot<?> entity) {
		EventStream uncommittedEvents = entity.getUncommittedEvents();
		while (uncommittedEvents.hasNext()) {
			Event<?> event = uncommittedEvents.next();
			this.saveEvent(event, entity.getType());
			EventBus.dispatch(event);
		}
	}

	/*
	 * Instancia a entidade e a reconstitui à partir dos seus eventos, invocando o
	 * método "reconstitute" usando reflection
	 */
	private void invokeEntityReconstituteMethod(AggregateRoot<?> entity, List<Event<?>> events)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> clazz = entity.getClass();
		while (true) {
			try {
				Method method = clazz.getDeclaredMethod("reconstitute", EventStream.class);
				method.setAccessible(true);
				method.invoke(entity, new EventStream(events));
				return;
			} catch (NoSuchMethodException ex) {
				clazz = clazz.getSuperclass();
				if (clazz == null) {
					throw new NoSuchMethodException(
							"Método 'reconstitute' não declarado na classe: " + entity.getClass().getName());
				}
			}
		}
	}

	/*
	 * Instancia a entidade usando reflection
	 */
	private <T extends AggregateRoot<?>> T instanceEntity(Class<T> entityType)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return entityType.getConstructor().newInstance();
	}
}
