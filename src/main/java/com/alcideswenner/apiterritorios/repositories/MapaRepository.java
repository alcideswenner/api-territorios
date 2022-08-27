package com.alcideswenner.apiterritorios.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.alcideswenner.apiterritorios.dto.RankingDTO;
import com.alcideswenner.apiterritorios.entities.Mapa;

public interface MapaRepository extends JpaRepository<Mapa, Long> {
    @Query(value = """
            WITH ORDEM_MAPA AS (SELECT TB_MAPA.id AS idMapa, TB_DESIGNACAO.data_carencia AS dataCarencia
                FROM TB_MAPA
                LEFT JOIN TB_DESIGNACAO ON TB_DESIGNACAO.DESIGNACAO_MAPA = TB_MAPA.ID
                ORDER BY TB_DESIGNACAO.data_conclusao)

            SELECT dataCarencia
                FROM ORDEM_MAPA
                WHERE idMapa= ?1 LIMIT 1;
            """, nativeQuery = true)
    Optional<LocalDateTime> findDataCarenciaOfMapaById(Long id);

    @Query(value = """
             WITH ORDEM_MAPA AS
              (SELECT TB_MAPA.ID AS IDMAPA,
            TB_USER.ID AS IDUSER,
                  TB_DESIGNACAO.DATA_DESIGNACAO
               FROM TB_MAPA
               LEFT JOIN TB_DESIGNACAO ON TB_DESIGNACAO.DESIGNACAO_MAPA = TB_MAPA.ID
               LEFT JOIN TB_USER ON TB_DESIGNACAO.DESIGNACAO_USER = TB_USER.ID
                  ORDER BY TB_DESIGNACAO.DATA_DESIGNACAO DESC)
             SELECT
                 IDUSER
             FROM ORDEM_MAPA
             WHERE IDMAPA = ?1
             LIMIT 1
             """, nativeQuery = true)
    Optional<Long> findIdUserByMapaId(Long idMapa);

    @Query(value = """
             WITH ORDEM_MAPA AS
              (SELECT TB_MAPA.ID AS IDMAPA,
            TB_USER.ID AS IDUSER,
                  TB_DESIGNACAO.DATA_DESIGNACAO,
                     TB_DESIGNACAO.ID AS IDDESIGNACAO
               FROM TB_MAPA
               LEFT JOIN TB_DESIGNACAO ON TB_DESIGNACAO.DESIGNACAO_MAPA = TB_MAPA.ID
               LEFT JOIN TB_USER ON TB_DESIGNACAO.DESIGNACAO_USER = TB_USER.ID
                  ORDER BY TB_DESIGNACAO.DATA_DESIGNACAO DESC)
             SELECT
             IDDESIGNACAO
             FROM ORDEM_MAPA
             WHERE IDMAPA = ?1
             LIMIT 1
             """, nativeQuery = true)
    Optional<Long> findIdDesignacaoByMapaId(Long idMapa);

    @Query(value = """
        SELECT new com.alcideswenner.apiterritorios.dto.RankingDTO(m.nome, COUNT(m.nome)) FROM Designacao d
        JOIN d.mapa m
		WHERE to_char(d.dataDesignacao, 'yyyy') = to_char(CURRENT_DATE, 'yyyy')
        GROUP BY m.nome
        ORDER BY COUNT(m.nome) DESC
            """)
    public List<RankingDTO> rankingBairros();
}
