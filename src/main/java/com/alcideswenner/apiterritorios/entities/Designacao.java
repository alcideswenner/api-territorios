package com.alcideswenner.apiterritorios.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_designacao")
public class Designacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataDesignacao;

    private LocalDateTime dataCarencia;

    private LocalDateTime dataConclusao;

    @OneToOne
    @JoinColumn(name = "designacao_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "designacao_mapa")
    private Mapa mapa;

}
