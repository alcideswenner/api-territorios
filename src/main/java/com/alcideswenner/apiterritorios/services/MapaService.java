package com.alcideswenner.apiterritorios.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alcideswenner.apiterritorios.dto.MapaDTO;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;

@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    public Optional<List<MapaDTO>> listaMapas() {
        List<MapaDTO> lista = mapaRepository.findAll().stream().map(e -> new MapaDTO(e)).toList();
        lista = lista.stream().map(e -> {
            Optional<Long> findIdUser = findIdUserByMapaId(e.getId());
            Optional<Long> findIdDesignacao = findIdDesignacaoByMapaId(e.getId());
            Optional<String> findDataCarencia = findDataCarenciaOfMapaById(e.getId());
            e.setMsgDataCarencia(findDataCarencia.orElseGet(String::new));
            e.setUserAtual(findIdUser.orElseGet(() -> 0L));
            e.setDesignacaoId(findIdDesignacao.orElseGet(() -> 0L));
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

    public Optional<String> findDataCarenciaOfMapaById(Long id) {
        Optional<LocalDateTime> optMapaDataCarencia = mapaRepository.findDataCarenciaOfMapaById(id);
        LocalDateTime dataAgora = LocalDateTime.now();
        if (!optMapaDataCarencia.isPresent()) {
            return Optional.of("");
        }
        if (optMapaDataCarencia.get().isAfter(dataAgora)) {
            long diferencaDias = ChronoUnit.DAYS.between(dataAgora, optMapaDataCarencia.get());
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

}
