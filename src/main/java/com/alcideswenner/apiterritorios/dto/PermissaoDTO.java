package com.alcideswenner.apiterritorios.dto;

import com.alcideswenner.apiterritorios.entities.Permissao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PermissaoDTO {

    private Long id;

    private String nomePermissao;

    private String descricaoPermissao;

    public PermissaoDTO(Permissao permissao){
        this.id = permissao.getIdPermissao();
        this.nomePermissao = permissao.getNomePermissao();
        this.descricaoPermissao = permissao.getNomePermissao();
    }

}