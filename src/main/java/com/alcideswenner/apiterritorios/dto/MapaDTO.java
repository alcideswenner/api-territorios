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

    public MapaDTO(Mapa mapa) {
        this.id = mapa.getId();
        this.urlMapa = mapa.getUrlMapa();
        this.nome = mapa.getNome();
        this.numeroTerritorio = mapa.getNumeroTerritorio();
        this.status = mapa.getStatus();
    }
}