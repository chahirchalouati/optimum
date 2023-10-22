package com.crcl.friend_ship;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

@EnableNeo4jRepositories("com.crcl.friend_ship.repository")
@ConfigurationPropertiesScan("com.crcl.friend_ship.configuration.*")
@SpringBootApplication
@EnableFeignClients
public class FriendShipServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendShipServiceApplication.class, args);
    }
    @Bean
    public ReactiveTransactionManager reactiveTransactionManager(Driver driver) {
        return  new ReactiveNeo4jTransactionManager(driver);
    }
    @Bean
    Configuration cypherDslConfiguration() {
        return Configuration.newConfig()
                .withDialect(Dialect.NEO4J_5).build();
    }
}