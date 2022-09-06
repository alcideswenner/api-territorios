package com.alcideswenner.apiterritorios.dto;

import java.time.LocalDateTime;
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

    public HistoricoDesignacaoDTO(DesignacaoDTO designacaoDTO) {
        this.idDesignacao = designacaoDTO.getId();
        this.idUser = designacaoDTO.getUser().getId();
        this.idMapa = designacaoDTO.getMapa().getId();
        this.nomeUser = designacaoDTO.getUser().getName();
        this.nomeMapa = "Território nº "+designacaoDTO.getMapa().getNumeroTerritorio();
        this.bairro = designacaoDTO.getMapa().getNome();
        this.dataDesignacao = designacaoDTO.getDataDesignacao();
        this.dataConclusao = designacaoDTO.getDataConclusao();
    }
}
