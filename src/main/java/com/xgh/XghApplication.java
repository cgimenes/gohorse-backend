package com.xgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// TODO testar undertow
// TODO ativar https no nginx
@SpringBootApplication
public class XghApplication {
    public static void main(String[] args) {
        SpringApplication.run(XghApplication.class, args);
    }

    @Configuration
    public static class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowCredentials(true)
                    .allowedMethods("*");
        }
    }

    @Configuration
    @EnableTransactionManagement
    public class JPAConfig{
        @Bean
        public PlatformTransactionManager transactionManager(){
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(new LocalContainerEntityManagerFactoryBean().getObject());
            return transactionManager;
        }
    }
}
