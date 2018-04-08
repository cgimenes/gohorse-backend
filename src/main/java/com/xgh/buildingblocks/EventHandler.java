package com.xgh.buildingblocks;

public interface EventHandler {
	/*
	 * Retorna true se esse event handler pode ser executado com um dado tipo de evento
	 */
    boolean isSubscribedTo(Event<?> event);

    void execute(Event<?> event);
}
