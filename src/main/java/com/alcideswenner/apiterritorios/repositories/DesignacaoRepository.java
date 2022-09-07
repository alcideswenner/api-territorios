package com.alcideswenner.apiterritorios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alcideswenner.apiterritorios.entities.Designacao;
import org.springframework.data.jpa.repository.Query;
import java.util.HashSet;

public interface DesignacaoRepository extends JpaRepository<Designacao, Long> {

    @Query("SELECT d FROM Designacao d JOIN FETCH d.user JOIN FETCH d.mapa ORDER BY d.dataDesignacao DESC")
    HashSet<Designacao> findJoinUserMapa();
}
