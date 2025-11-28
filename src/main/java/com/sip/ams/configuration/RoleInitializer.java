package com.sip.ams.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sip.ams.entities.ERole;
import com.sip.ams.entities.Role;
import com.sip.ams.repositories.RoleRepository;

@Configuration
public class RoleInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            for (ERole erole : ERole.values()) {

                roleRepository.findByName(erole).orElseGet(() -> {
                            Role role = new Role(erole);
                            return roleRepository.save(role);
                        });
            }

            System.out.println(">>> Roles initialisés !");
        };
    }
}
