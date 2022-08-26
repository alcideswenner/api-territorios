package com.alcideswenner.apiterritorios.dto;

import com.alcideswenner.apiterritorios.entities.Mapa;

import lombok.*;

@Data
@NoArgsConstructor
public class MapaDTO {
    
    private Long id;

    private String urlMapa;

    private String nome;

    private Integer numeroTerritorio;

    private Boolean status;

    private String msgDataCarencia;

    private Long userAtual;

    private Long designacaoId;
    
    private String urlGoogleMaps;

    public MapaDTO(Mapa mapa) {
        this.id = mapa.getId();
        this.urlMapa = mapa.getUrlMapa();
        this.nome = mapa.getNome();
        this.numeroTerritorio = mapa.getNumeroTerritorio();
        this.status = mapa.getStatus();
        this.msgDataCarencia = mapa.getMsgDataCarencia();
        this.userAtual = mapa.getUserAtual();
        this.designacaoId = mapa.getDesignacaoId();
        this.urlGoogleMaps = mapa.getUrlGoogleMaps();
    }
}
