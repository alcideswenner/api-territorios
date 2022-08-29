package com.alcideswenner.apiterritorios.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.dto.RankingDTO;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;

@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    public Optional<List<MapaDTO>> listaMapas(Long idUser, String nome) {
        List<MapaDTO> lista = mapaRepository.listaMapasWithLeftJoinMapaAndDesignacao();
        if (idUser != null) {
            lista = lista.stream().filter(e -> e.getUserAtual().equals(idUser) &&
                    e.getStatus() == true).toList();
        }
        if (nome != null) {
            lista = lista.stream().filter(e -> e.getNome().equals(nome)).toList();
        }

        lista = lista.stream().map(e -> {
            Optional<String> findDataCarencia = findDataCarenciaOfMapaById(e.getDataCarencia());
            e.setMsgDataCarencia(findDataCarencia.orElseGet(String::new));
            return e;
        }).toList();

        return Optional.of(lista);
    }

    public Optional<MapaDTO> findMapaByID(Long id) {
        return Optional.of(mapaRepository
                .findById(id)
                .stream()
                .map(e -> new MapaDTO(e))
                .findFirst()
                .orElseGet(null));
    }

    public Optional<String> findDataCarenciaOfMapaById(LocalDateTime dataCarencia) {
        LocalDateTime dataAgora = LocalDateTime.now();
        Optional<LocalDateTime> optDataCarencia = Optional.ofNullable(dataCarencia);
        if (!optDataCarencia.isPresent()) {
            return Optional.of("");
        }
        if (optDataCarencia.get().isAfter(dataAgora)) {
            long diferencaDias = ChronoUnit.DAYS.between(dataAgora, optDataCarencia.get());
            return Optional.of("(Território usado recentemente) - Aguarde " + diferencaDias
                    + " dias para trabalhar com esse território novamente");
        } else {
            return Optional.of("");
        }
    }

    public Optional<Long> findIdUserByMapaId(Long id) {
        Optional<Long> optIdUser = mapaRepository.findIdUserByMapaId(id);

        if (!optIdUser.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(optIdUser.get());
    }

    public Optional<Long> findIdDesignacaoByMapaId(Long id) {
        Optional<Long> optIdDesignacao = mapaRepository.findIdDesignacaoByMapaId(id);

        if (!optIdDesignacao.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(optIdDesignacao.get());
    }

    public Optional<List<RankingDTO>> rankingBairros() {
        try {
            Optional<List<RankingDTO>> lista = Optional.ofNullable(mapaRepository
                    .rankingBairros().stream().limit(5).toList());
            return lista;
        } catch (NoResultException | NonUniqueResultException e) {
            return Optional.empty();
        }
    }
}
