package com.xgh.buildingblocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventBus {
	private static Logger logger = LogManager.getLogger(EventBus.class);
	
    private List<EventHandler> handlers = new ArrayList<>();

    /*
     * Singleton
     */
    private static EventBus instance;
    private EventBus() {}
    private static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public static void addHandler(EventHandler eventHandler) {
        getInstance().handlers.add(eventHandler);
    }

    public static void addAllHandlers(Collection<? extends EventHandler> eventHandlers) {
        getInstance().handlers.addAll(eventHandlers);
    }

    /*
     * Dispara o evento para todos os handlers cadastrados e cada handler decide se
     * irá ou não ser executado para o tipo do evento que foi disparado 
     */
    public static void dispatch(Event<?> event) {
    	logger.info("Disparando evento: %s com os dados: %s", event.getClass().getName(), event.toJson());
        for (EventHandler handler : getInstance().handlers) {
            if (handler.isSubscribedTo(event)) {
            	logger.info("Executando event handler: %s", handler.getClass().getName());
            	try {
                    handler.execute(event);
            	} catch (Exception ex) {
            		logger.fatal("Erro ao executar event handler", ex);
            	}
            }
        }
    }
}
