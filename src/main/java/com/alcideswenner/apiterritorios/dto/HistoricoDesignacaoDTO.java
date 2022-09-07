package com.alcideswenner.apiterritorios.dto;

import java.time.LocalDateTime;
import com.alcideswenner.apiterritorios.entities.Designacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoricoDesignacaoDTO {
    
    private Long idDesignacao;
    
    private Long idUser;

    private Long idMapa;

    private String nomeUser;

    private String nomeMapa;

    private String bairro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalDateTime dataDesignacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalDateTime dataConclusao;

    public HistoricoDesignacaoDTO(Designacao e) {
        this.idDesignacao = e.getId();
        this.idUser = e.getUser().getId();
        this.idMapa = e.getMapa().getId();
        this.nomeUser = e.getUser().getName();
        this.nomeMapa = "Território nº "+e.getMapa().getNumeroTerritorio();
        this.bairro = e.getMapa().getNome();
        this.dataDesignacao = e.getDataDesignacao();
        this.dataConclusao = e.getDataConclusao();
    }
}
