package org.hostel.config;

import akka.actor.ActorSystem;
import org.hostel.actor.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AkkaConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akka-system");
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(system)
                .initialize(applicationContext);
        return system;
    }
}

