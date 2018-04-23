package com.xgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO testar undertow
// TODO ativar https no nginx
@SpringBootApplication
public class XghApplication {
    public static void main(String[] args) {
        SpringApplication.run(XghApplication.class, args);
    }
}
