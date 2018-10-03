package com.xgh.buildingblocks.event;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventBus {
    private static final Logger logger = LogManager.getLogger(EventBus.class);

    private final List<EventHandler> handlers = new ArrayList<>();

    /*
     * Singleton
     */
    private static EventBus instance;

    private EventBus() {
    }

    private static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public static void addHandler(EventHandler handler) {
        logger.info(String.format("Adicionando handler '%s' ao EventBus", handler.getClass().getName()));
        getInstance().handlers.add(handler);
    }

    /*
     * Dispara o evento para todos os handlers cadastrados e cada handler decide se
     * irá ou não ser executado para o tipo do evento que foi disparado
     */
    public static void dispatch(Event event) {
        logger.info(String.format("Disparando evento: %s com os dados: %s", event.getClass().getName(), event.toJson()));
        for (EventHandler handler : getInstance().handlers) {
            if (handler.isSubscribedTo(event)) {
                logger.info(String.format("Executando event handler: %s", handler.getClass().getName()));
                try {
                    // TODO adicionar async
                    handler.execute(event);
                } catch (Exception ex) {
                    logger.fatal("Erro ao executar event handler", ex);
                }
            }
        }
    }
}
