package com.alcideswenner.apiterritorios.sqlResultDto;

import javax.persistence.*;
import com.alcideswenner.apiterritorios.dto.RetornaMapasNaoConcluidosDTO;
import com.alcideswenner.apiterritorios.entities.Mapa;

@Entity
@NamedNativeQuery(name = "find_mapas_nao_concluidos_dto", query = """
        SELECT m.nome AS nome, m.numero_territorio AS numeroTerritorio FROM tb_designacao d
        JOIN tb_mapa m ON m.id = d.designacao_mapa
        where d.data_conclusao IS NULL
            """, resultSetMapping = "find_mapas_nao_concluidos")
@SqlResultSetMapping(name = "find_mapas_nao_concluidos", classes = @ConstructorResult(targetClass = RetornaMapasNaoConcluidosDTO.class, columns = {
        @ColumnResult(name = "nome", type = String.class),
        @ColumnResult(name = "numeroTerritorio", type = Integer.class),
}))
public class ResultSqlMapa extends Mapa {
}
