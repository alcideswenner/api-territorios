package com.alcideswenner.apiterritorios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingDTO {

    private String bairro;
    private Long qtd;

    public RankingDTO(String bairro, Long qtd) {
        this.bairro = bairro;
        this.qtd = qtd;
    }
}
