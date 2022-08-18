package com.alcideswenner.apiterritorios.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_mapa")
public class Mapa {
        
    @Id
    private Long id;

    private Byte imagem;

    private String nome;

    private Integer numeroTerritorio;

    @OneToOne(mappedBy = "mapa")
    private Designacao designacao;
}
