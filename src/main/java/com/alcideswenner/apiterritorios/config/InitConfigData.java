package com.alcideswenner.apiterritorios.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alcideswenner.apiterritorios.entities.Permissao;
import com.alcideswenner.apiterritorios.entities.User;
import com.alcideswenner.apiterritorios.repositories.PermissaoRepository;
import com.alcideswenner.apiterritorios.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitConfigData {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PermissaoRepository permissaoRepository) {
        return (args) -> {

            if (userRepository.findByUsername("admin") == null) {
                Permissao permissao = new Permissao();
                permissao.setDescricao("ACESSO GLOBAL");
                permissao.setNomePermissao("SYSTEM");

                User user = new User();
                user.setName("Wenner");
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));

                permissao.setUser(user);
                user.setPermissao(Arrays.asList(permissao));
                userRepository.save(user);
            }

        };
    }

}
