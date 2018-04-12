package com.xgh.buildingblocks.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandBus {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Map<Class<? extends Command>, CommandHandler<?>> handlers = new HashMap<>();

    /*
     * Singleton
     */
    private static CommandBus instance;
    private CommandBus() {}
    private static CommandBus getInstance() {
        if (instance == null) {
            instance = new CommandBus();
        }
        return instance;
    }
    
    public static void addHandler(Class<? extends Command> command, CommandHandler<?> handler) {
        getInstance().handlers.put(command, handler);
    }

    public static void addAllHandlers(Map<Class<? extends Command>, CommandHandler<?>> handlers) {
        getInstance().handlers.putAll(handlers);
    }

    /*
     * Executa um comando utilizando o seu respectivo command handler
     */
    @SuppressWarnings("unchecked")
    public static <T extends Command> void dispatch(T command) {
        getInstance().logger.info(String.format("Executando comando: %s; com os dados: %s", command.getClass().getName(), command.toJson()));

        CommandHandler<T> handler = (CommandHandler<T>) getInstance().handlers.get(command.getClass());
        
        if (handler == null) {
            throw new RuntimeException(String.format("Nenhum handler encontrado para o comando: %s", command.getClass().getName()));
        }
        
        handler.execute(command);
    }
}
