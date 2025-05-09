package org.example.config;

import org.example.Desktop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Bean(name = {"com2","com3","desktop3"}) you can give multiple names
    @Bean(name = "com2")
    public Desktop desktop(){
        return new Desktop();
    }
}
