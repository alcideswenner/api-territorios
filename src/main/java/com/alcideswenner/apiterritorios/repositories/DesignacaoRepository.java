package com.alcideswenner.apiterritorios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alcideswenner.apiterritorios.entities.Designacao;

public interface DesignacaoRepository extends JpaRepository<Designacao, Long> {
    
}
