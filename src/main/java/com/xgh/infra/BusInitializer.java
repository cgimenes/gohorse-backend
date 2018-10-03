package com.xgh.infra;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.buildingblocks.event.EventHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BusInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final DefaultListableBeanFactory context;
    private final Reflections reflections = new Reflections("com.xgh");

    @Autowired
    public BusInitializer(DefaultListableBeanFactory context) {
        this.context = context;
    }

    /*
     * Pesquisa todas as classes do tipo CommandHandler que estão dentro do pacote com.xgh,
     * obtém a classe de command que o command handler executa,
     * obtém uma instância do command handler usando o container de injeção do Spring
     * e adiciona no CommandBus
     */
    private void initializeCommandBus() {
        logger.info("Inicializando command bus");

        Set<Class<? extends CommandHandler>> handlers = reflections.getSubTypesOf(CommandHandler.class);
        for (Class<? extends CommandHandler> handler : handlers) {
            CommandBus.addHandler(getCommandClass(handler), context.getBean(handler));
        }
    }

    /*
     * Pesquisa todas as classes do tipo EventHandler que estão dentro do pacote com.xgh,
     * obtém uma instância do event handler usando o container de injeção do Spring
     * e adiciona no EventBus
     */
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
    private Class<? extends EntityCommand> getCommandClass(Class<? extends CommandHandler> handlerClass) {
        ParameterizedType parameterizedType = (ParameterizedType) handlerClass.getGenericInterfaces()[0];
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        return (Class<? extends EntityCommand>) typeArguments[0];
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeCommandBus();
        initializeEventBus();
        logger.info("Bus inicializados");
    }
}
