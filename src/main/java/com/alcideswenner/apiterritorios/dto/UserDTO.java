package com.alcideswenner.apiterritorios.dto;

import java.util.List;
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

    private List<Designacao> designacao;

    private List<Permissao> permissao;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.designacao = user.getDesignacao();
        this.permissao = user.getPermissao();
    }
}
