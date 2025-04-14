package com.springbook.application.sjhm.API_springboot;


import com.springbook.application.sjhm.orm.jpa.InMemoryUniqueIdGenerator;
import com.springbook.application.sjhm.orm.jpa.UniqueIdGenerator;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CopsbootApplicationConfiguration {

  @Bean
  public UniqueIdGenerator<UUID> uniqueIdGenerator() {
    return new InMemoryUniqueIdGenerator();
  }
}
