package com.springbook.application.sjhm.API_springboot.Repository.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.springbook.application.sjhm.API_springboot.Model.User.User;
import com.springbook.application.sjhm.API_springboot.Model.User.UserRole;
import com.springbook.application.sjhm.orm.jpa.UniqueIdGenerator;
import com.springbook.application.sjhm.orm.jpa.InMemoryUniqueIdGenerator;

import java.util.HashSet;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testStoreUser() {
    HashSet<UserRole> roles = new HashSet<>();
    roles.add(UserRole.OFFICER);
    User user = repository.save(new User(repository.nextId(),
                            "alex.foley@beverly-hills.com",
                            "my-secret-pwd",
                            roles));
    
    assertThat(user).isNotNull();
    assertThat(repository.count()).isEqualTo(1L);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> generator() {
            return new InMemoryUniqueIdGenerator();
        }
    }
}