package com.alcideswenner.apiterritorios.sqlResultDto;

import java.time.LocalDateTime;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.entities.Mapa;

@Entity
@NamedNativeQuery(name = "find_all_mapas", query = """
        SELECT m.id AS id,
        m.status AS status,
        m.url_google_maps AS urlGoogleMaps,
        m.url_mapa AS urlMapa,
        m.nome AS nome,
        m.numero_territorio AS numeroTerritorio,
        d.data_carencia AS dataCarencia,
        d.id  AS designacaoId,
        u.id AS userAtual
    FROM TB_MAPA m
    LEFT JOIN TB_DESIGNACAO d ON (d.DESIGNACAO_MAPA = m.ID AND d.id =
        (SELECT MAX(TB_DESIGNACAO.id)
        FROM TB_DESIGNACAO
        WHERE TB_DESIGNACAO.DESIGNACAO_MAPA = d.designacao_mapa))
    LEFT JOIN tb_user u ON u.id = d.designacao_user
            """, resultSetMapping = "find_all_mapas_result")
@SqlResultSetMapping(name = "find_all_mapas_result", classes = @ConstructorResult(targetClass = MapaDTO.class, columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "urlMapa", type = String.class),
        @ColumnResult(name = "nome", type = String.class),
        @ColumnResult(name = "numeroTerritorio", type = Integer.class),
        @ColumnResult(name = "status", type = Boolean.class),
        @ColumnResult(name = "dataCarencia", type = LocalDateTime.class),
        @ColumnResult(name = "userAtual", type = Long.class),
        @ColumnResult(name = "designacaoId", type = Long.class),
        @ColumnResult(name = "urlGoogleMaps", type = String.class),
}))
public class ResultSqlMapa extends Mapa {
}
