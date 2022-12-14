package com.alcideswenner.apiterritorios.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @JoinColumn(name = "id_user")
    private User user;

    public Permissao(PermissaoDTO dto) {
        this.idPermissao = dto.getId();
        this.nomePermissao = dto.getNomePermissao();
        this.descricao = dto.getDescricaoPermissao();
    }
}
