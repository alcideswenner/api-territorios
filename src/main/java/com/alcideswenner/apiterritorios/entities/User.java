package com.alcideswenner.apiterritorios.entities;

import java.util.List;
import javax.persistence.*;
import com.alcideswenner.apiterritorios.dto.UserDTO;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Designacao> designacao;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissao;

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.designacao = userDTO.getDesignacao();
        this.permissao = userDTO.getPermissao().stream().map(e -> {
            e.setUser(this);
            return e;
        }).toList();
    }

    public void setPermissao(List<Permissao> permissao) {
        this.permissao = permissao.stream().map(e -> {
            e.setUser(this);
            return e;
        }).toList();
    }

}
