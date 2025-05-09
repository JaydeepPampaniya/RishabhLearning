package org.example.config;

import org.example.Computer;
import org.example.Desktop;
import org.example.Laptop;
import org.example.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    public Programmer programmer(@Autowired Computer com){ //@Qualifier("desktop") otherwise you can use primary annotation
        Programmer pr = new Programmer();
        pr.setAge(20);
        pr.setCom(com);
        return pr;
    }

    //    @Bean(name = {"com2","com3","desktop3"}) you can give multiple names
    @Bean(name = "com2")
    // @Scope("prototype")
    public Desktop desktop(){
        return new Desktop();
    }

    @Bean
    // @Scope("prototype")
    @Primary
    public Laptop laptop(){
        return new Laptop();
    }
}
