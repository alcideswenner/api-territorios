package com.alcideswenner.apiterritorios.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.alcideswenner.apiterritorios.entities.Mapa;
import com.alcideswenner.apiterritorios.entities.Permissao;
import com.alcideswenner.apiterritorios.entities.User;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;
import com.alcideswenner.apiterritorios.repositories.PermissaoRepository;
import com.alcideswenner.apiterritorios.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitConfigData {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner init(UserRepository userRepository, PermissaoRepository permissaoRepository,
            MapaRepository mapaRepository) {
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

            if (mapaRepository.count() == 0) {
                Mapa[] mapas = new Mapa[29];
                mapas[0] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map1.png", "Centro", 1, false,
                        null);
                mapas[1] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map2.png", "Centro", 2, false,
                        null);
                mapas[2] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map3.png", "Centro", 3, false,
                        null);
                mapas[3] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map4.png", "Centro", 4, false,
                        null);
                mapas[4] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map5.png", "Centro", 5, false,
                        null);
                mapas[5] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map6.png", "Bela Vista", 6, false,
                        null);
                mapas[6] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map7.png", "Bela Vista", 7, false,
                        null);
                mapas[7] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map8.png", "São Francisco", 8,
                        false,
                        null);
                mapas[8] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map9.png", "Sarney", 9, false,
                        null);
                mapas[9] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/ma10.png", "Sarney", 10, false,
                        null);
                mapas[10] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map11.png", "Subestação", 11,
                        false,
                        null);
                mapas[11] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map12.png", "Subestação", 12,
                        false,
                        null);
                mapas[12] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map13.png", "Subestação", 13,
                        false,
                        null);
                mapas[13] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map14.png", "Quiabos", 14, false,
                        null);
                mapas[14] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map15.png", "Novo Tempo", 15,
                        false,
                        null);
                mapas[15] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map16.png", "Quiabos", 16, false,
                        null);
                mapas[16] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map17.png", "Quiabos", 17, false,
                        null);
                mapas[17] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map18.png", "Quiabos", 18, false,
                        null);
                mapas[18] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map19.png", "Multirão", 19, false,
                        null);
                mapas[19] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map20.png", "Multirão", 20, false,
                        null);
                mapas[20] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map21.png", "Olho D'Aguinha", 21,
                        false,
                        null);
                mapas[21] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map22.png", "Olho D'Aguinha", 22,
                        false,
                        null);
                mapas[22] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map23.png", "Olho D'Aguinha", 23,
                        false,
                        null);
                mapas[23] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map24.png", "Anil", 24, false,
                        null);
                mapas[24] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map25.png", "Anil", 25, false,
                        null);
                mapas[25] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map26.png", "Anil", 26, false,
                        null);
                mapas[26] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map27.png", "Anil", 27, false,
                        null);
                mapas[27] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map28.png", "Anil", 28, false,
                        null);
                mapas[28] = new Mapa(0L, "https://territorio-api.herokuapp.com/mapas/map29.png", "Anil", 29, false,
                        null);

                for (Mapa m : mapas) {
                    mapaRepository.save(m);
                }
            }
        };
    }

}
