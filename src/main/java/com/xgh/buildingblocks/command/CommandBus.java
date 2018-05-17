package com.xgh.buildingblocks.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandBus {
    private static final Logger logger = LogManager.getLogger(CommandBus.class);

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
        logger.info(String.format("Adicionando handler '%s' do comando '%s' ao CommandBus", handler.getClass().getName(), command.getName()));
        getInstance().handlers.put(command, handler);
    }

    /*
     * Executa um comando utilizando o seu respectivo command handler
     */
    @SuppressWarnings("unchecked")
    public static <T extends Command> void dispatch(T command) {
        logger.info(String.format("Executando comando: %s; com os dados: %s", command.getClass().getName(), command.toJson()));

        CommandHandler<T> handler = (CommandHandler<T>) getInstance().handlers.get(command.getClass());

        if (handler == null) {
            throw new RuntimeException(String.format("Nenhum handler encontrado para o comando: %s", command.getClass().getName()));
        }

        handler.execute(command);
    }
}
