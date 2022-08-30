package com.alcideswenner.apiterritorios.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alcideswenner.apiterritorios.dto.DesignacaoDTO;
import com.alcideswenner.apiterritorios.entities.Designacao;
import com.alcideswenner.apiterritorios.exceptions.DesignacaoNotFoundException;
import com.alcideswenner.apiterritorios.repositories.DesignacaoRepository;
import com.alcideswenner.apiterritorios.repositories.MapaRepository;

@Service
public class DesignacaoService {

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private DesignacaoRepository designacaoRepository;

    private final UserService userService;

    public DesignacaoService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private NotificationPushService notificationPushService;

    public Optional<Designacao> saveDesignacao(Designacao designacao) {
        userService.isUserReal(designacao.getUser().getId());
        mapaRepository.findById(designacao.getMapa().getId()).map(e -> {
            e.setStatus(true);
            return e;
        });

        return Optional.of(designacaoRepository.save(designacao));
    }

    public Optional<List<DesignacaoDTO>> findAll() {
        return Optional.of(designacaoRepository.findJoinUserMapa().stream().map(DesignacaoDTO::new).toList());
    }

    @Transactional
    public Optional<Boolean> concluirDesignacao(Long id) {
        Designacao designacao = designacaoRepository.findById(id)
                .orElseThrow(() -> new DesignacaoNotFoundException("Designação não encontrada"));
        userService.isUserReal(designacao.getUser().getId());
        
        if (designacao.getDataConclusao() != null) {
            return Optional.of(Boolean.FALSE);
        }

        designacao.setDataConclusao(LocalDateTime.now());
        designacao.setDataCarencia(LocalDateTime.now().plusDays(8));
        designacao.getMapa().setStatus(false);

        try {
            notificationPushService.requestSendPush(
                    "Território nº " + designacao.getMapa().getNumeroTerritorio() +
                            "\n Bairro: " +
                            designacao.getMapa().getNome(),
                    "Nova Designação Concluída");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.of(Boolean.TRUE);
    }
}
