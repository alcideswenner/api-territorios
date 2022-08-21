package com.alcideswenner.apiterritorios.dto;

import java.time.LocalDateTime;
import com.alcideswenner.apiterritorios.entities.Designacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DesignacaoDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalDateTime dataDesignacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalDateTime dataCarencia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalDateTime dataConclusao;

    private UserDTO user;

    private MapaDTO mapa;

    public DesignacaoDTO(Designacao designacao) {
        this.id = designacao.getId();
        this.dataDesignacao = designacao.getDataDesignacao();
        this.dataCarencia = designacao.getDataCarencia();
        this.dataConclusao = designacao.getDataConclusao();
        this.user = new UserDTO(designacao.getUser());
        this.mapa = new MapaDTO(designacao.getMapa());
    }

}
