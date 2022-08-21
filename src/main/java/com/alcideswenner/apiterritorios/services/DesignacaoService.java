package com.alcideswenner.apiterritorios.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alcideswenner.apiterritorios.dto.DesignacaoDTO;
import com.alcideswenner.apiterritorios.entities.Designacao;
import com.alcideswenner.apiterritorios.repositories.DesignacaoRepository;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;

@Service
public class DesignacaoService {

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private DesignacaoRepository designacaoRepository;

    public Optional<Designacao> saveDesignacao(Designacao designacao) {
        
        mapaRepository.findById(designacao.getMapa().getId()).map(e -> {
            e.setStatus(true);
            return e;
        });

        return Optional.of(designacaoRepository.save(designacao));
    }

    public Optional<List<DesignacaoDTO>> findAll(){
        return Optional.of(designacaoRepository.findJoinUserMapa().stream().map(DesignacaoDTO::new).toList());
    }
}
