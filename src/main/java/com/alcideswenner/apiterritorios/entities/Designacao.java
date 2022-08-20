package com.alcideswenner.apiterritorios.entities;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_designacao")
public class Designacao {
        
    @Id
    private Long id;

    private LocalDateTime dataDesignacao;

    private LocalDateTime dataCarencia;

    private LocalDateTime dataConclusao;

    @ManyToOne
    @JoinColumn(name = "designacao_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "designacao_mapa")
    private Mapa mapa;
}
