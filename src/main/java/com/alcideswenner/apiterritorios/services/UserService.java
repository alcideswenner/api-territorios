package com.alcideswenner.apiterritorios.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alcideswenner.apiterritorios.dto.UserDTO;
import com.alcideswenner.apiterritorios.entities.User;
import com.alcideswenner.apiterritorios.exceptions.UserAuthenticationIsRealException;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;
import com.alcideswenner.apiterritorios.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapaRepository mapaRepository;

    public Optional<User> saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
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

    public Optional<List<UserDTO>> findAll() {
        return Optional.of(userRepository.findAll().stream().map(user -> new UserDTO(user)).toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        long[] idsMapa = mapaRepository.listaMapasWithLeftJoinMapaAndDesignacao()
                .stream()
                .filter(e -> e.getUserAtual()
                        .equals(id))
                .mapToLong(e -> e.getId()).toArray();
        if (idsMapa != null) {
            for (long l : idsMapa) {
                mapaRepository.findById(l).map(e -> {
                    e.setStatus(false);
                    return e;
                });
            }
        }
        userRepository.deleteById(id);
    }

    public void isUserReal(Long idUser) {
        Optional<User> optUser = userRepository.findById(idUser);
        String usernameAuth = SecurityContextHolder.getContext().getAuthentication().getName();
        Boolean isRoleNotSystem = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .filter(e -> e.getAuthority().equals("SYSTEM")).count() == 0;

        if (!optUser.isPresent()) {
            throw new UserAuthenticationIsRealException("Usuário não existe!");
        }

        if ((!usernameAuth.equals(optUser.get().getUsername())) && isRoleNotSystem) {
            throw new UserAuthenticationIsRealException("Alerta! Ataque de Hacker.");
        }
    }
}
