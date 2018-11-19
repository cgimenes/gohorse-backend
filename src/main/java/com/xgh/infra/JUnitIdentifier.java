package com.xgh.infra;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class JUnitIdentifier implements ApplicationListener<ContextRefreshedEvent> {
    private static boolean junitTest;

    public static boolean isJUnitTest() {
        return junitTest;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        junitTest = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith("org.junit.")) {
                junitTest = true;
                break;
            }
        }
    }
}
