package com.crcl.story;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.neo4j.driver.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.ReactiveTransactionManager;

@EnableNeo4jRepositories("com.crcl.story.*")
@ConfigurationPropertiesScan("com.crcl.story.configuration.*")
@SpringBootApplication
@EnableFeignClients
public class StoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoryServiceApplication.class, args);
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
