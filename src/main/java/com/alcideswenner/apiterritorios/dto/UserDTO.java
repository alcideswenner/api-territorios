package com.alcideswenner.apiterritorios.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alcideswenner.apiterritorios.entities.Designacao;
import com.alcideswenner.apiterritorios.entities.Permissao;
import com.alcideswenner.apiterritorios.entities.User;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String name;

    private List<PermissaoDTO> permissao = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.permissao = user.getPermissao().stream().map(e -> new PermissaoDTO(e)).toList();
    }
}
