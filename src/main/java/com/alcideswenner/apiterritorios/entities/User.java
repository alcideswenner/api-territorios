package com.alcideswenner.apiterritorios.entities;

import java.util.List;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Designacao> designacao;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissao;

    public void setPermissao(List<Permissao> permissao) {
        this.permissao = permissao.stream().map(e -> {
            e.setUser(this);
            return e;
        }).toList();
    }
}
