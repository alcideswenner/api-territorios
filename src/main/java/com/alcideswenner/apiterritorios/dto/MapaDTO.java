package com.alcideswenner.apiterritorios.dto;

import java.time.LocalDateTime;

import com.alcideswenner.apiterritorios.entities.Mapa;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapaDTO {

    private Long id;

    private String urlMapa;

    private String nome;

    private Integer numeroTerritorio;

    private Boolean status;

    private LocalDateTime dataCarencia;

    private Long userAtual;

    private Long designacaoId;

    private String urlGoogleMaps;

    private String msgDataCarencia;

    private String nomeUserAtual;

    public MapaDTO(Long id, String urlMapa, String nome, Integer numeroTerritorio, Boolean status,
            LocalDateTime dataCarencia, Long userAtual, Long designacaoId, String urlGoogleMaps, String nomeUserAtual) {
        this.id = id;
        this.urlMapa = urlMapa;
        this.nome = nome;
        this.numeroTerritorio = numeroTerritorio;
        this.status = status;
        this.dataCarencia = dataCarencia;
        this.userAtual = userAtual;
        this.designacaoId = designacaoId;
        this.urlGoogleMaps = urlGoogleMaps;
        this.nomeUserAtual = nomeUserAtual;
    }

    public MapaDTO(Mapa mapa) {
        this.id = mapa.getId();
        this.urlMapa = mapa.getUrlMapa();
        this.nome = mapa.getNome();
        this.numeroTerritorio = mapa.getNumeroTerritorio();
        this.status = mapa.getStatus();
        this.dataCarencia = mapa.getDataCarencia();
        this.userAtual = mapa.getUserAtual();
        this.designacaoId = mapa.getDesignacaoId();
        this.urlGoogleMaps = mapa.getUrlGoogleMaps();
    }

    public Long getUserAtual() {
        if (this.userAtual != null) {
            return this.userAtual;
        }
        return 0L;
    }
}
