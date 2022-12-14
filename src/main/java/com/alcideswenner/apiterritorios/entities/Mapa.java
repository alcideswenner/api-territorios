package com.alcideswenner.apiterritorios.entities;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_mapa")
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlMapa;

    private String nome;

    private Integer numeroTerritorio;

    private Boolean status;

    private String urlGoogleMaps;

    @Transient
    private LocalDateTime dataCarencia;

    @Transient
    private Long userAtual;

    @Transient
    private Long designacaoId;

    @OneToMany(mappedBy = "mapa")
    private List<Designacao> designacao;

    public Mapa(Long id, String urlMapa, String nome, Integer numeroTerritorio, Boolean status,
            List<Designacao> designacao, String urlGoogleMaps) {
        this.id = id;
        this.urlMapa = urlMapa;
        this.nome = nome;
        this.numeroTerritorio = numeroTerritorio;
        this.status = status;
        this.designacao = designacao;
        this.urlGoogleMaps = urlGoogleMaps;
    }
}
