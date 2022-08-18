package com.alcideswenner.apiterritorios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alcideswenner.apiterritorios.entities.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Permissao findByNomePermissao(String nomePermissao);
}
