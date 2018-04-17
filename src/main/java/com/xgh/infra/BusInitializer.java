package com.xgh.infra;

import com.xgh.buildingblocks.command.Command;
import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.buildingblocks.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

@Component
public class BusInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private DefaultListableBeanFactory context;

    private Reflections reflections = new Reflections("com.xgh");

    @Autowired
    public BusInitializer(DefaultListableBeanFactory context) {
        this.context = context;
    }

    private void initializeCommandBus() {
        logger.info("Inicializando command bus");

        Set<Class<? extends CommandHandler>> handlers = reflections.getSubTypesOf(CommandHandler.class);
        for (Class<? extends CommandHandler> handler : handlers) {
            CommandBus.addHandler(getCommandClass(handler), context.getBean(handler));
        }
    }

    private void initializeEventBus() {
        logger.info("Inicializando event bus");

        Set<Class<? extends EventHandler>> handlers = reflections.getSubTypesOf(EventHandler.class);
        for (Class<? extends EventHandler> handler : handlers) {
            EventBus.addHandler(context.getBean(handler));
        }
    }

    /*
     * Obtém a classe do comando que um dado command handler executa, usando reflection
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Command> getCommandClass(Class<? extends CommandHandler> handlerClass) {
        ParameterizedType parameterizedType = (ParameterizedType) handlerClass.getGenericInterfaces()[0];
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        return (Class<? extends Command>) typeArguments[0];
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeCommandBus();
        initializeEventBus();
        logger.info("Bus inicializados");
    }
}
