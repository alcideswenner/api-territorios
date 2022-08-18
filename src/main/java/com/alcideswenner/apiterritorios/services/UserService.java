package com.alcideswenner.apiterritorios.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.alcideswenner.apiterritorios.dto.UserDTO;
import com.alcideswenner.apiterritorios.entities.User;
import com.alcideswenner.apiterritorios.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<UserDTO> saveUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = userRepository.save(new User(userDTO));
        return Optional.of(user).map(e -> new UserDTO(e));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não localizado!");
        }
        Collection<SimpleGrantedAuthority> permissoes = new ArrayList<SimpleGrantedAuthority>();
        user.getPermissao().forEach(e -> {
            permissoes.add(new SimpleGrantedAuthority(e.getNomePermissao()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                permissoes);
    }
}
