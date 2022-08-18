package com.alcideswenner.apiterritorios.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alcideswenner.apiterritorios.dto.PermissaoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_PERMISSAO")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissao;

    private String nomePermissao;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "permissao_user")
    private User user;

    public Permissao(PermissaoDTO dto) {
        this.idPermissao = dto.getId();
        this.nomePermissao = dto.getNomePermissao();
        this.descricao = dto.getDescricaoPermissao();
    }
}
